#ifndef __CONFIG_H__
#define __CONFIG_H__

#include "globals.h"

typedef struct
{
  char host[200];
  int port;
  char serialname[100];
  char crypt_key_txt[100];
  char crypt_iv_txt[100];

} bridge_config;



bridge_config* get_bridge_config();

void load_bridge_config(char* fileName);


#endif