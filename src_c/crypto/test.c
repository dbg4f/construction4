#include <stdio.h>
#include <stdlib.h>

#include "base64.h"
#include "mbed-aes.h"

int main(void)
{

    int i;
    unsigned char key[32];
    unsigned char buf[64];
    unsigned char iv[16];
    int v = MBEDTLS_AES_ENCRYPT;

    mbedtls_aes_context ctx;

    char str[100];

        mbedtls_aes_init( &ctx );



        memset( key, 0, 32 );
        memset( iv , 0, 16 );

        memset( buf, 0, 32 );

        strcpy(buf, "{\"test\":123}");

        mbedtls_aes_setkey_enc( &ctx, key, 128);

        mbedtls_aes_crypt_cbc( &ctx, v, 32, iv, buf, buf );


        for (i=0; i<16; i++)
        {
          printf("%d ", buf[i]);
        }




      puts("\n");

      Base64encode(str, buf, 32);

      printf("str = %s\n", str);



}

