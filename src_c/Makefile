# TOOLCHAIN_DIR=/usr/local/cross-compile/openwrt/staging_dir/toolchain-mips_34kc_gcc-4.8-linaro_uClibc-0.9.33.2

INCLUDE_DIR=$(TOOLCHAIN_DIR)/usr/include

CC=mips-openwrt-linux-uclibc-gcc

CFLAGS= -std=gnu99

LDFLAGS=-pthread

SOURCES=bridge.c jsmn.c buffer.c

OBJS=$(SOURCES:.c=.o)

all: bridge_mips

bridge.o: bridge.c
	$(CC) -c $(CFLAGS)  -I $(INCLUDE_DIR) -o $@ $<
  
buffer.o: buffer.c
	$(CC) -c $(CFLAGS)  -I $(INCLUDE_DIR) -o $@ $<

jsmn.o: jsmn.c
	$(CC) -c $(CFLAGS)  -I $(INCLUDE_DIR) -o $@ $<

%.o: %.c %.h
	$(CC) -c $(CFLAGS) -I $(INCLUDE_DIR) -o $@ $<

bridge_mips: $(OBJS)
	$(CC) $(LDFLAGS) $(CFLAGS) -o bridge_mips $(OBJS)

clean:
	rm *.o bridge_mips
