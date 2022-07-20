package com.artemvasin.com.sensor.services;

import com.artemvasin.com.sensor.models.Sensor;
import com.artemvasin.com.sensor.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void newSensor(Sensor sensor) {
        sensorsRepository.save(sensor);
        System.out.println("Новый датчик успешно добавлен");
    }

    public Optional<Sensor> findByName(String name) {
        return sensorsRepository.findByName(name);
    }

}
