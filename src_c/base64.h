#ifndef __BASE64_H__
#define __BASE64_H__

int Base64decode(char *bufplain, const char *bufcoded);

int Base64encode(char *encoded, const char *string, int len);

#endif