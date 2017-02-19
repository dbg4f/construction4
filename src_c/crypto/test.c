#include <stdio.h>
#include <stdlib.h>

#include "base64.h"
#include "mbed-aes.h"

int main(void)
{

    int i;
    const char* tKey = "i2FcCFdj6TgqZyqTaDQK/g==";
    const char* tIv = "aUzcCjsw1uXA5DIYTfJLrA==";
    unsigned char key[16];
    unsigned char buf[64];
    unsigned char buf2[64];
    unsigned char iv[16];
    int v = MBEDTLS_AES_ENCRYPT;

    mbedtls_aes_context ctx;

    char str[100];

        mbedtls_aes_init( &ctx );



        memset( key, 0, 16 );
        memset( iv , 0, 16 );

        memset( buf, 0, 64 );
        memset( buf2, 0, 64 );

        strcpy(buf, "{\"test\":123}");

        mbedtls_aes_setkey_enc( &ctx, key, 128);

        mbedtls_aes_crypt_cbc( &ctx, v, 16, iv, buf, buf );


        for (i=0; i<16; i++)
        {
          printf("%d ", buf[i]);
        }




      puts("\n");

      Base64encode(str, buf, 32);

      printf("str = %s\n", str);


     //----------------------------------

      Base64decode(key, tKey);
      Base64decode(iv, tIv);

      memset( buf, ' ', 64 );

      strcpy(buf, "{\"test\":123}");

      mbedtls_aes_setkey_enc( &ctx, key, 128);

      mbedtls_aes_crypt_cbc( &ctx, v, 16, iv, buf, buf2 );

      Base64encode(str, buf2, 16);

      printf("str1 = %s %s\n", str, buf);

//----------------------------------

      Base64decode(key, tKey);
      Base64decode(iv, tIv);

      memset( buf, ' ', 64 );

      strcpy(buf, "{\"test\":123}");

      mbedtls_aes_setkey_enc( &ctx, key, 128);

      mbedtls_aes_crypt_cbc( &ctx, v, 16, iv, buf, buf2 );

      Base64encode(str, buf2, 16);

      printf("str1 = %s %s\n", str, buf);



}

