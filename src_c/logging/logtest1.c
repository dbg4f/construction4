#include <stdarg.h>
#include <syslog.h>

void log_msg(int priority, const char *format, ...)
{
    va_list vl;

    va_start(vl, format);
    vsyslog(priority, format, vl);
    va_end(vl);
}

int main(void)
{
    log_msg(LOG_USER|LOG_DEBUG, "%d variable %s\n", 2, "arguments");
    return 0;
}