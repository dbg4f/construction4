#include "globals.h"

#define MAX_FMT_SIZE 200

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
            error_message("HTTP POST: ERROR writing message to socket");
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
            error_message("HTTP POST: ERROR reading response from socket");
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



void* readSerialThread()
{
	bridge_config* pConfig;

	pConfig = get_bridge_config();
	
    log_print(1, "Opening %s", pConfig->serialname);

    while (1) {

      int fd = open (pConfig->serialname, O_RDWR | O_NOCTTY | O_SYNC);

      if (fd < 0)
      {
              error_message ("error %d opening %s: %s, will try once more after several seconds", errno, pConfig->serialname, strerror (errno));
              sleep(3);
              continue;

      }

      log_print(1, "Opening %s OK", pConfig->serialname);

    set_interface_attribs (fd, B115200, 0);  // set speed to 115,200 bps, 8n1 (no parity)
    set_blocking (fd, 1);                // set no blocking


    char buf [300];


    while (1) {

		memset(buf, 0, 300);

		int n = read (fd, buf, sizeof(buf));

		if (n > 0) {
			log_print(1, "read %d: %s\n", n, buf);        
			buf_consume(buf, 0, n);
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

pthread_t serial_tid;

int main (int argc, char **argv) {

	char buf[300];
	char bufEncrypted[300];
    char bufEncryptedTxt[300];
	char bufParam[300];
    int err;
	bridge_config* pConfig;

    log_print(1, "Serial-to-HTTP bridge, version "__DATE__" "__TIME__);
	
    load_bridge_config("bridge-config.json");
	
	pConfig = get_bridge_config();

    buf_init();

    crypt_init();

    if (pthread_create(&(serial_tid), NULL, &readSerialThread, NULL) != 0)
    {
      error_message ("error %d creating serial reading thread ", errno);
    }


	while(1)
	{

			while(buf_get_next(buf))
			{

              // TODO: check buffer is representing valid JSON
              // TODO: wait on semaphore to react immediately on data appearance
              // TODO: protect simultaneously accessible data with mutex
              // TODO: add "sample=" prefix, needed to correctly extract data from POST request in servlet
              // TODO: logging level configurable
              // TODO: install as a daemon

			
				crypt_encrypt(buf, bufEncrypted, bufEncryptedTxt);

				strcpy(bufParam, "encrypted=");

				strcat(bufParam, bufEncryptedTxt);
			
				http_post(pConfig->host, pConfig->port, bufParam);
			
			}

			sleep(1);

    }



}