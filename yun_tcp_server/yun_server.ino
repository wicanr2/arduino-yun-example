#include <Bridge.h>
#include <YunClient.h>
#include <YunServer.h>

YunServer *server = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  Bridge.begin();
  server = new YunServer(5566);
  server->noListenOnLocalhost();
  server->begin();
}


char buf[128];

void loop() {
  // put your main code here, to run repeatedly:
  int read = 0;
  
 delay(1000);
   YunClient  client = server->accept();  
 
  if ( client.connected() ) {
    if ( client.available() ) {
       memset(buf,0,128);
       //echo server
         read = client.read((uint8_t*)buf,128);
         Serial.println("initial socket");
         Serial.print("recv : ");  Serial.println(buf);
         client.write((const uint8_t*)buf, read);
         while( client.connected() ) {
              memset(buf,0,128);
              strcpy(buf, "I am Yun Server");
            client.write((uint8_t*)buf, strlen(buf) );
            Serial.print("send response : ");  Serial.println(buf);
            delay(1000);
            
         }
         Serial.print("leave while ");  
    }
  } else {
    client.stop();
  }
}
