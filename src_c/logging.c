#include "globals.h"

#define BR_LOG_STD   0x01
#define BR_LOG_SYS   0x02

//----------------------------------------------------------------------------------------------------------------------------------

#define STD_OUT_VARARGS \
    va_list argptr; \
    va_start(argptr, format); \
    timestamp(); \
    vfprintf(stderr, format, argptr); \
    va_end(argptr); \
    puts("");


#define SYS_OUT_VARARGS \
    va_list vl; \
    va_start(vl, format); \
    vsyslog(LOG_USER|LOG_INFO, format, vl); \
    va_end(vl);

/*
void log_msg_sys(const char *format, ...)
{
    va_list vl;
    va_start(vl, format);
    vsyslog(LOG_USER|LOG_INFO, format, vl);
    va_end(vl);
}
*/
//----------------------------------------------------------------------------------------------------------------------------------


int log_target_mask = 0xff;

void set_log_target_mask(int mask)
{
    log_print(1, "config: log_target_mask=%d\n", mask);
    log_target_mask = mask;
}



//----------------------------------------------------------------------------------------------------------------------------------


int log_level = 0;

void set_log_level(int level)
{
    log_print(1, "config: log_level=%d\n", level);
    log_level = level;
}

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
    if (log_level <= dbg_lvl)
    {
        //log_print(format);

        if (log_target_mask & BR_LOG_STD)
        {
            STD_OUT_VARARGS
        }

        if (log_target_mask & BR_LOG_SYS)
        {
            SYS_OUT_VARARGS
        }


    }

}

void error_message(const char* format, ...)
{
    if (log_level <= BR_LEVEL_ERROR)
     {
        if (log_target_mask & BR_LOG_STD)
         {
             STD_OUT_VARARGS
         }

         if (log_target_mask & BR_LOG_SYS)
         {
             SYS_OUT_VARARGS
         }
     }
}


//----------------------------------------------------------------------------------------------------------------------------------


int channel_statuses[2] = {-1, -1};

void channel_message(int channel, int status, const char* format, ...)
{
    if (channel_statuses[channel] != status)
    {
        channel_statuses[channel] = status;
        log_print(BR_LEVEL_INFO, format);
    }
    else
    {
        log_print(BR_LEVEL_DEBUG, format);
    }
}

//----------------------------------------------------------------------------------------------------------------------------------


long lastTs = 0;

void log_rarely(const char* format, ...)
{
    long t = time(NULL);

    if (t > (lastTs + 60*5))
    {
        lastTs = t;
        log_print(BR_LEVEL_INFO, format);
    }

}