#include "mbed.h"
#include "ESP8266Interface.h"
#include "TCPSocketConnection.h"
#include "TCPSocketServer.h"
#include "Websocket.h"


Serial pc(USBTX, USBRX);


ESP8266Interface wifi(PTC4,PTC3,PTC0,"buffalo78","sipsik1234567",115200); // TX,RX,Reset,SSID,Password,Baud
 
int main() {
    
    pc.baud(57600);  // set what you want here depending on your terminal program speed
    pc.printf("\f\n\r-------------ESP8266 Hardware Reset-------------\n\r");

    
    printf("Start\n");
    
    wifi.init(); //Reset
    wifi.connect(); //Use DHCP
    
    printf("Connected\n");
    
    //printf("IP Address is %s\n", wifi.getIPAddress());
    
    Websocket ws("ws://192.168.10.104:8000/ws");
    
    //Check for successful socket connection
    if(!ws.connect())
        ws.close();
    else{
        char str[100];
        
        for(int i=0; i<0x7fffffff; ++i) {
            // string with a message
            sprintf(str, "%d WebSocket Hello World over wifi", i);
            printf("send: %s\n",str);
            ws.send(str);
        
            // clear the buffer and wait a sec...
            memset(str, 0, 100);
            wait(0.5f);
        
            // websocket server should echo whatever we sent it
            if (ws.read(str)) {
                printf("rcv'd: %s\n", str);
            }
        }
    }
    ws.close();
    
    while(true) {}
}
 