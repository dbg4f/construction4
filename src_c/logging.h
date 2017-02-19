#ifndef __LOGGING_H__
#define __LOGGING_H__

#define BR_LEVEL_DEBUG    0
#define BR_LEVEL_INFO     1
#define BR_LEVEL_ERROR    2

#define BR_CHANNEL_SERIAL 0
#define BR_CHANNEL_HTTP    1

#define BR_STATUS_ERROR   0
#define BR_STATUS_OK      1


void set_log_level(int level);

void log_print(int dbg_lvl, const char *format, ...);

void log_info(const char *format, ...);

void log_debug(const char *format, ...);

void log_error(const char *format, ...);

void error_message(const char* format, ...);

void log_channel_status(int channel, int status, const char* format, ...);

void log_rarely(const char* format, ...);

void set_log_target_mask(int mask);


#endif