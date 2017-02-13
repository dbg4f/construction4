#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <string.h>
#include <termios.h>
#include <unistd.h>
#include <stdarg.h>
#include <time.h>
#include <sys/socket.h> /* socket, connect */
#include <netinet/in.h> /* struct sockaddr_in, struct sockaddr */
#include <netdb.h> /* struct hostent, gethostbyname */
#include <pthread.h>


#include "logging.h"
#include "config.h"
#include "jsmn.h"
#include "buffer.h"
#include "mbed-aes.h"
#include "crypt.h"
