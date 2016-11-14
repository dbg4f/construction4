#include "buffer.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define BUF_CAPACITY 5
#define MAX_LEN 300

char elements[BUF_CAPACITY][MAX_LEN];

int elementIndex = 0;

int head = 0;
int tail = 0;

int filled[BUF_CAPACITY];

void cs_enter()
{

}

void cs_leave()
{

}

int valid_index(int i, int capacity)
{
    return i >= 0 && i < capacity;
}

int next_index(int i, int capacity)
{
    i++;
    if (i >= capacity)
    {
        i = 0;
    }
    return i;
}

void buf_add_one(char c)
{
    if (valid_index(head, BUF_CAPACITY) && valid_index(elementIndex, MAX_LEN))
    {
        elements[head][elementIndex++] = c;
    }
}

void buf_init()
{
    for (int i=0; i<BUF_CAPACITY; i++)
    {
        filled[i] = 0;
        memset(elements[i], 0, MAX_LEN);
    }
    head = 0;
    tail = 0;
}

void buf_switch_head()
{
    filled[head] = 1;
    head = next_index(head, BUF_CAPACITY);
    memset(elements[head], 0, MAX_LEN);
    elementIndex = 0;
}


void buf_consume(char* buf, int start, int length)
{
    cs_enter();

    for (int i=start; i<start+length; i++)
    {
        buf_add_one(buf[i]);

        if (buf[i] == '\n')
         {
            buf_switch_head();
         }

    }

    cs_leave();
}

int buf_get_next(char* buf)
{
    cs_enter();

    int copied = 0;

    if (filled[tail] != 0)
    {
        strcpy(buf, elements[tail]);
        filled[tail] = 0;
        tail = next_index(tail, BUF_CAPACITY);

        copied = 1;
    }


    cs_leave();

    return copied;

}

/*
void dump()
{
    printf("head %d  tail %d   ind %d\n", head, tail, elementIndex);
    for (int i=0; i<BUF_CAPACITY; i++)
    {
        printf("filled %d - %s\n", filled[i], elements[i]);
    }

}

void  drain_buf(char* mark)
{
    char buf[MAX_LEN];
    while(buf_get_next(buf))
    {
        printf("%s:%s\n", mark, buf);
    }
    printf("%s---------\n", mark);
    //dump();
}

int main()
{
    puts("111");
    buf_init();
    buf_consume("1111222222", 0, 10);
    drain_buf("1");
    buf_consume("AAA\nBBBBBBBBBBBBB", 0, 10);
    drain_buf("2");
    buf_consume("CCC\nEEEEEEEEE", 0, 10);
    dump();
    drain_buf("3");

}

*/