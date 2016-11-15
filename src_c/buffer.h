#ifndef __BUFFER_H_
#define __BUFFER_H_

#include <stdlib.h>
#include <stdio.h>
#include <string.h>


void buf_init();

void buf_consume(char* buf, int start, int length);

int buf_get_next(char* buf);

#endif