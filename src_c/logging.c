#include "globals.h"

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

