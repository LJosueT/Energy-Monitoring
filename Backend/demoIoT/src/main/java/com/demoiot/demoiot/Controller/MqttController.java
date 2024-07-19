package com.demoiot.demoiot.Controller;

import com.demoiot.demoiot.Service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") @CrossOrigin(origins = "*")
public class MqttController {

    @Autowired
    private MqttService mqttService;

    @GetMapping("/corriente")
    public float getCorriente() {
        return mqttService.getCorriente();
    }

    @GetMapping("/potencia")
    public float getPotencia() {
        return mqttService.getPotencia();
    }
}