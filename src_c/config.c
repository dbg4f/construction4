#include "globals.h"


bridge_config config;


bridge_config* get_bridge_config()
{
    return &config;
}



static int jsoneq(const char *json, jsmntok_t *tok, const char *s) {
	if (tok->type == JSMN_STRING && (int) strlen(s) == tok->end - tok->start &&
			strncmp(json + tok->start, s, tok->end - tok->start) == 0) {
		return 0;
	}
	return -1;
}

static void get_json_value(char* buffer, jsmntok_t *t, int r, char* key, char* value) {

        int length = 0;
        int i;

        strcpy(value, "");

    	for (i = 1; i < r; i++) {

    		if (jsoneq(buffer, &t[i], key) == 0) {

                length = t[i+1].end-t[i+1].start;
    			strncpy(value, buffer + t[i+1].start, length);
    			value[length] = 0;
    		}
    		i++;
    	}
}


void load_bridge_config(char* fileName)
{

    char * buffer = 0;
    long length;

    char value[100];

    log_print(1, "Opening config file %s", fileName);

    FILE * f = fopen (fileName, "rb");

      if (f < 0)
      {
           error_message ("error %d opening %s: %s, will try once more after several seconds", errno, fileName, strerror (errno));
      }
    else
    {
      fseek (f, 0, SEEK_END);
      length = ftell (f);
      fseek (f, 0, SEEK_SET);
      buffer = malloc (length);
      if (buffer)
      {
         fread (buffer, 1, length, f);
      }
      fclose (f);
    }


  if (buffer)
  {
    log_print(1, "Opening config file OK, read %d", length);

	int i;
	int r;
	jsmn_parser p;
	jsmntok_t t[300];

	jsmn_init(&p);
	r = jsmn_parse(&p, buffer, strlen(buffer), t, sizeof(t)/sizeof(t[0]));
	if (r < 0) {
		log_print(0, "Failed to parse JSON: %d\n", r);

	}
	/* Assume the top-level element is an object */
	if (r < 1 || t[0].type != JSMN_OBJECT) {
		log_print(0, "Error parsing jason config: Object expected\n");
        return;
	}

    get_json_value(buffer, t, r, "host",        config.host);
    get_json_value(buffer, t, r, "serialname",  config.serialname);
    get_json_value(buffer, t, r, "key",         config.crypt_key_txt);
    get_json_value(buffer, t, r, "iv",          config.crypt_iv_txt);

    get_json_value(buffer, t, r, "port", value);
    config.port = atoi(value);

    log_print(1, "config: host=%s, port=%d, serialname=%s\n", config.host, config.port, config.serialname);

  }

  free(buffer);



}
