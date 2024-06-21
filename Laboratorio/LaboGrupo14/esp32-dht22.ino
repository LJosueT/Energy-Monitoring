#include "DHTesp.h"
#include <WiFi.h>
#include <PubSubClient.h>


const int DHT_PIN = 4;
const char* ssid = "Wokwi-GUEST";
const char* password = "";
const char* mqtt_server = "test.mosquitto.org";

WiFiClient espClient;
PubSubClient client(espClient);
DHTesp dhtSensor;

unsigned long lastMsg = 0;

void setup() {
  Serial.begin(115200);
  delay(10);

  // Conectar a WiFi
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(2000);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  // Configurar MQTT
  client.setServer(mqtt_server, 1883);

  // Configurar sensor DHT22
  dhtSensor.setup(DHT_PIN, DHTesp::DHT22);
}

void reconnect() {
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    String clientId = "ESP32Client-";
    clientId += String(random(0xffff), HEX);
    if (client.connect(clientId.c_str())) {
      Serial.println("connected");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      delay(5000);
    }
  }
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  unsigned long now = millis();
  if (now - lastMsg > 2000) {
    lastMsg = now;
    TempAndHumidity data = dhtSensor.getTempAndHumidity();
    
    String temp = String(data.temperature, 2);
    Serial.print("Temperature: ");
    Serial.println(temp);
    client.publish("/indobot/temp", temp.c_str());
    
    String hum = String(data.humidity, 1);
    Serial.print("Humidity: ");
    Serial.println(hum);
    client.publish("/indobot/hum", hum.c_str());
  }
}