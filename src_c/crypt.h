#ifndef __CRYPT_H__
#define __CRYPT_H__

#include "globals.h"

void crypt_init();

void crypt_encrypt(char* input, char* output, char* outputTxt);

#endif