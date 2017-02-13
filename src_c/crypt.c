#include "globals.h"


    int i;
    //const char* tKey = "i2FcCFdj6TgqZyqTaDQK/g==";
    //const char* tIv = "aUzcCjsw1uXA5DIYTfJLrA==";
    unsigned char key[16];
    unsigned char buf[64];
    unsigned char buf2[64];
    unsigned char iv[16];
    int v = MBEDTLS_AES_ENCRYPT;

    mbedtls_aes_context ctx;

    char str[100];


void crypt_init()
{
    mbedtls_aes_init( &ctx );

    // TODO: read key and iv from config, convert from base64
}

void crypt_encrypt(char* input, char* output)
{


}