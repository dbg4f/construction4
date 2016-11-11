# TOOLCHAIN_DIR=/usr/local/cross-compile/openwrt/staging_dir/toolchain-mips_34kc_gcc-4.8-linaro_uClibc-0.9.33.2

INCLUDE_DIR=$(TOOLCHAIN_DIR)/usr/include

CC=mips-openwrt-linux-uclibc-gcc

CFLAGS= -std=gnu99

SOURCES=bridge.c jsmn.c

OBJS=$(SOURCES:.c=.o)

all: bridge

bridge.o: bridge.c
	$(CC) -c $(CFLAGS)  -I $(INCLUDE_DIR) -o $@ $<
  
jsmn.o: jsmn.c
	$(CC) -c $(CFLAGS)  -I $(INCLUDE_DIR) -o $@ $<

%.o: %.c %.h
	$(CC) -c $(CFLAGS) -I $(INCLUDE_DIR) -o $@ $<

bridge: $(OBJS)
	$(CC) $(LDFLAGS) $(CFLAGS) -o bridge $(OBJS)

clean:
	rm *.o bridge
