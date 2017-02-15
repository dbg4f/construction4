#include "globals.h"


    int i;
    //const char* tKey = "i2FcCFdj6TgqZyqTaDQK/g==";
    //const char* tIv = "aUzcCjsw1uXA5DIYTfJLrA==";
    unsigned char key[16];
    unsigned char iv[16];
    //int v = MBEDTLS_AES_ENCRYPT;

    mbedtls_aes_context ctx;

    char str[100];


void crypt_init()
{
	mbedtls_aes_init( &ctx );

    // TODO: read key and iv from config, convert from base64
	
	bridge_config* pConfig = get_bridge_config();
	
	memset( key, 0, 16 );
	memset( iv , 0, 16 );

	Base64decode(key, pConfig->crypt_key_txt);
	Base64decode(iv, pConfig->crypt_iv_txt);
      
	mbedtls_aes_setkey_enc( &ctx, key, 128);


	
}

void crypt_encrypt(char* input, char* output)
{
	
	// TODO: align buffer size 
	
	mbedtls_aes_crypt_cbc( &ctx, MBEDTLS_AES_ENCRYPT, 16, iv, input, output );
	
	Base64encode(output, output, 16);
	
	
	

}