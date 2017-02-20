#include "mbed.h"
#include "DHT.h"
#include "MbedJSONValue.h"
#include "USBSerial.h"

 
DigitalOut myled(LED1);
 
DHT sensor(PTD4,DHT22); 
 
Serial pc(USBTX, USBRX);

//USBSerial pc;

int counter = 0; 
 
int main() {
    int err;

    pc.baud(115200);

    //pc.printf("\r\nDHT Test program");
    //pc.printf("\r\n******************\r\n");
    wait(1); // wait 1 second for device stable status
    while (1) {
        myled = 0;
        err = sensor.readData();
        
        MbedJSONValue sensor1;
        sensor1["sensorType"]     = "DHT22";
        sensor1["sensorID"]     = "1";
        sensor1["ct"]     = counter++;
                    
        if (err == 0) {
            /*
            pc.printf("Temperature is %4.2f C \r\n",sensor.ReadTemperature(CELCIUS));
            pc.printf("Temperature is %4.2f F \r\n",sensor.ReadTemperature(FARENHEIT));
            pc.printf("Temperature is %4.2f K \r\n",sensor.ReadTemperature(KELVIN));
            pc.printf("Humidity is %4.2f \r\n",sensor.ReadHumidity());
            pc.printf("Dew point is %4.2f  \r\n",sensor.CalcdewPoint(sensor.ReadTemperature(CELCIUS), sensor.ReadHumidity()));
            pc.printf("Dew point (fast) is %4.2f  \r\n",sensor.CalcdewPointFast(sensor.ReadTemperature(CELCIUS), sensor.ReadHumidity()));
              */          
            sensor1["t"]     = sensor.ReadTemperature(CELCIUS);
            sensor1["h"]     = sensor.ReadHumidity();
            sensor1["dew"]    = sensor.CalcdewPoint(sensor.ReadTemperature(CELCIUS), sensor.ReadHumidity());    
            
        } 
        else 
        {
            sensor1["error"]     = err;
        }
            
        pc.printf(sensor1.serialize().c_str());
        pc.printf("\n");
                
        myled = 1;
        wait(0.5);
        myled = 0;
        wait(2);
    }
}