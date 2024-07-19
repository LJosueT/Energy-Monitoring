package com.demoiot.demoiot.Service;

import com.demoiot.demoiot.Entity.Datos;
import com.demoiot.demoiot.Repository.DatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    @Autowired
    private DatosRepository datosRepository;

    private float corriente;
    private float potencia;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) {
        String payload = (String) message.getPayload();
        String[] data = payload.split(",");
        if (data.length == 2) {
            corriente = Float.parseFloat(data[0]);
            potencia = Float.parseFloat(data[1]);
            Datos datos = new Datos(null, corriente, potencia);
            datosRepository.save(datos);
            System.out.println("Corriente: " + corriente + " A, Potencia: " + potencia + " W");
        }
    }

    public float getCorriente() {
        return corriente;
    }

    public float getPotencia() {
        return potencia;
    }
}