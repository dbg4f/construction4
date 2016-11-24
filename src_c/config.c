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


void load_bridge_config(char* fileName)
{

    char * buffer = 0;
    long length;

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

  strcpy(config.host, "localhost");
  strcpy(config.serialname, "/dev/ttyACM0");
  config.port = 8080;

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
		printf("Failed to parse JSON: %d\n", r);

	}
	/* Assume the top-level element is an object */
	if (r < 1 || t[0].type != JSMN_OBJECT) {
		printf("Object expected\n");

	}

	/* Loop over all keys of the root object */
	for (i = 1; i < r; i++) {
		if (jsoneq(buffer, &t[i], "user") == 0) {
			/* We may use strndup() to fetch string value */
			printf("- User: %.*s\n", t[i+1].end-t[i+1].start,
					buffer + t[i+1].start);
			i++;
		} else if (jsoneq(buffer, &t[i], "admin") == 0) {
			/* We may additionally check if the value is either "true" or "false" */
			printf("- Admin: %.*s\n", t[i+1].end-t[i+1].start,
					buffer + t[i+1].start);
			i++;
		} else if (jsoneq(buffer, &t[i], "uid") == 0) {
			/* We may want to do strtol() here to get numeric value */
			printf("- UID: %.*s\n", t[i+1].end-t[i+1].start,
					buffer + t[i+1].start);
			i++;
		} else if (jsoneq(buffer, &t[i], "groups") == 0) {
			int j;
			printf("- Groups:\n");
			if (t[i+1].type != JSMN_ARRAY) {
				continue; /* We expect groups to be an array of strings */
			}
			for (j = 0; j < t[i+1].size; j++) {
				jsmntok_t *g = &t[i+j+2];
				printf("  * %.*s\n", g->end - g->start, buffer + g->start);
			}
			i += t[i+1].size + 1;
		} else {
			printf("Unexpected key: %.*s\n", t[i].end-t[i].start,
					buffer + t[i].start);
		}
	}



  }

  free(buffer);



}
