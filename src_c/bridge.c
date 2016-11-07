#include <errno.h>
#include <fcntl.h>
#include <string.h>
#include <termios.h>
#include <unistd.h>
#include <stdarg.h>
#include <stdio.h>
#include <time.h>
#include <stdlib.h> /* exit */
#include <sys/socket.h> /* socket, connect */
#include <netinet/in.h> /* struct sockaddr_in, struct sockaddr */
#include <netdb.h> /* struct hostent, gethostbyname */


#define MAX_FMT_SIZE 200

//----------------------------------------------------------------------------------------------------------------------------------

void timestamp() {
  time_t rawtime;
  struct tm * timeinfo;

  time ( &rawtime );
  timeinfo = localtime ( &rawtime );

  //printf("[%s] - ", asctime (timeinfo) );

  fprintf(stderr, "%s", asctime (timeinfo));

}

//----------------------------------------------------------------------------------------------------------------------------------

void log_print(int dbg_lvl, const char *format, ...)
{

    va_list argptr;
    va_start(argptr, format);
    timestamp();
    vfprintf(stderr, format, argptr);
    va_end(argptr);
    puts("");

}

//----------------------------------------------------------------------------------------------------------------------------------

void error_message(const char* format, ...)
{


    va_list argptr;
    va_start(argptr, format);
    timestamp();
    vfprintf(stderr, format, argptr);
    va_end(argptr);
    puts("");
}

//----------------------------------------------------------------------------------------------------------------------------------

int set_interface_attribs (int fd, int speed, int parity)
{
        struct termios tty;
        memset (&tty, 0, sizeof tty);

        if (tcgetattr (fd, &tty) != 0)
        {
                error_message ("error %d from tcgetattr", errno);
                return -1;
        }

        cfsetospeed (&tty, speed);
        cfsetispeed (&tty, speed);

        tty.c_cflag = (tty.c_cflag & ~CSIZE) | CS8;     // 8-bit chars
        // disable IGNBRK for mismatched speed tests; otherwise receive break
        // as \000 chars
        tty.c_iflag &= ~IGNBRK;         // disable break processing
        tty.c_lflag = 0;                // no signaling chars, no echo,
                                        // no canonical processing
        tty.c_oflag = 0;                // no remapping, no delays
        tty.c_cc[VMIN]  = 0;            // read doesn't block
        tty.c_cc[VTIME] = 5;            // 0.5 seconds read timeout

        tty.c_iflag &= ~(IXON | IXOFF | IXANY); // shut off xon/xoff ctrl

        tty.c_cflag |= (CLOCAL | CREAD);// ignore modem controls,
                                        // enable reading
        tty.c_cflag &= ~(PARENB | PARODD);      // shut off parity
        tty.c_cflag |= parity;
        tty.c_cflag &= ~CSTOPB;
        tty.c_cflag &= ~CRTSCTS;

        if (tcsetattr (fd, TCSANOW, &tty) != 0)
        {
                error_message ("error %d from tcsetattr", errno);
                return -1;
        }
        return 0;
}

//----------------------------------------------------------------------------------------------------------------------------------

void set_blocking (int fd, int should_block)
{
        struct termios tty;
        memset (&tty, 0, sizeof tty);
        if (tcgetattr (fd, &tty) != 0)
        {
                error_message ("error %d from tggetattr", errno);
                return;
        }

        tty.c_cc[VMIN]  = should_block ? 1 : 0;
        tty.c_cc[VTIME] = 5;            // 0.5 seconds read timeout

        if (tcsetattr (fd, TCSANOW, &tty) != 0)
                error_message ("error %d setting term attributes", errno);
}

//----------------------------------------------------------------------------------------------------------------------------------

int http_post(char* host, int port, char* data)
{
    /* first what are we going to send and where are we going to send it? */
    //int portno =        8089;
    //char *host =        "localhost";
    //char *message_fmt = "POST /data=%s HTTP/1.0\r\n\r\n";

    char *message_fmt =
                     "POST /telemetry HTTP/1.1\r\n"
                     "Host: %s:%d\r\n"
                     "Content-Length: %d\r\n"
                     "Pragma: no-cache\r\n"
                     "Cache-Control: no-cache\r\n"
                     "Accept: text/html,application/xhtml+xml,application/xml\r\n"
                     "Content-Type: application/x-www-form-urlencoded\r\n\r\n"
                      "%s";



    struct hostent *server;
    struct sockaddr_in serv_addr;
    int sockfd, bytes, sent, received, total;
    char message[1024],response[4096];

    /* fill in the parameters */
    sprintf(message,message_fmt, host, port, strlen(data), data);


    log_print(1, "Request (%s:%d):\n%s\n", host, port, message);

    /* create the socket */
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0)
    {
      log_print(0, "HTTP POST: ERROR opening socket");
      return -1;
    }

    /* lookup the ip address */
    server = gethostbyname(host);
    if (server == NULL)
    {
      log_print(0, "HTTP POST: ERROR, no such host");
      return -2;
    }

    /* fill in the structure */
    memset(&serv_addr,0,sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(port);
    memcpy(&serv_addr.sin_addr.s_addr,server->h_addr,server->h_length);

    /* connect the socket */
    if (connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)) < 0)
    {
        log_print(0, "HTTP POST: ERROR connecting");
        return -3;
    }

    /* send the request */
    total = strlen(message);
    sent = 0;
    do {
        bytes = write(sockfd,message+sent,total-sent);

        //log_print(1, "Bytes  write %d %d %d", bytes, sent, total);

        if (bytes < 0)
            error("HTTP POST: ERROR writing message to socket");
        if (bytes == 0)
            break;
        sent+=bytes;
    } while (sent < total);

    /* receive the response */
    memset(response,0,sizeof(response));
    total = sizeof(response)-1;
    received = 0;
    do {
        bytes = read(sockfd,response+received,total-received);

        log_print(1, "Bytes  read %d %d %d", bytes, received, total);

        if (bytes < 0)
            error("HTTP POST: ERROR reading response from socket");
        if (bytes == 0)
            break;

        if (bytes > 0)
            break;

        received+=bytes;
    } while (received < total);


    /* close the socket */
    close(sockfd);

    /* process response */
    log_print(1, "Response:\n%s\n",response);

    return 0;
}

main (int argc, char **argv) {

    char portname[100];
    char host[200];
    int port = 8080;

    log_print(1, "Serial-to-HTTP bridge, version "__DATE__" "__TIME__);


    //strcpy(portname, "/dev/pts/11");
    strcpy(portname, "/dev/ttyACM0");

    if (argc >= 2) {
      strcpy(portname, argv[1]);
    }

    strcpy(host, "localhost");

    if (argc >= 3) {
      strcpy(host, argv[2]);
    }

    if (argc >= 4) {
      port = atoi(argv[3]);
    }


    http_post(host, port, "sample=342342135");


    log_print(1, "Opening %s", portname);

    while (1) {

      int fd = open (portname, O_RDWR | O_NOCTTY | O_SYNC);

      if (fd < 0)
      {
              error_message ("error %d opening %s: %s, will try once more after several seconds", errno, portname, strerror (errno));
              sleep(3);
              continue;

      }

      log_print(1, "Opening %s OK", portname);

    set_interface_attribs (fd, B115200, 0);  // set speed to 115,200 bps, 8n1 (no parity)
    set_blocking (fd, 1);                // set no blocking

    //write (fd, "hello!\n", 7);           // send 7 character greeting

    //usleep ((7 + 25) * 100);             // sleep enough to transmit the 7 plus
                                         // receive 25:  approx 100 uS per char transmit
    char buf [300];

    //memset(buf, 0, 300);

    while (1) {

       memset(buf, 0, 300);

      int n = read (fd, buf, sizeof(buf));

      //log_print(1, "read %d: %s\n", n, buf);

      if (n > 0) {
        log_print(1, "read %d: %s\n", n, buf);
        //http_post(host, port, buf);
      }

      if (n <= 0) {
        log_print(1, "read failed, will re-try %d: %s\n", n, buf);
        close(fd);
        sleep(3);
        break;
      }


    }

    }


}