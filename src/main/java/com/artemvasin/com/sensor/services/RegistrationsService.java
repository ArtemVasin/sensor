package com.artemvasin.com.sensor.services;

import com.artemvasin.com.sensor.models.Registration;
import com.artemvasin.com.sensor.repositories.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RegistrationsService {

    private final RegistrationsRepository registrationsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public RegistrationsService(RegistrationsRepository registrationsRepository, SensorsService sensorsService) {
        this.registrationsRepository = registrationsRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void newRegistrations(Registration registration) {
        enrichRegistrations(registration);
        registrationsRepository.save(registration);
        System.out.println("Новое значение успешно добалено");
    }

    public void enrichRegistrations(Registration registration) {
        registration.setSensor(sensorsService.findByName(registration.getSensor().getName()).get());

        registration.setDateOfMeasurement(LocalDateTime.now());
    }


    public List<Registration> registrationList() {
        return registrationsRepository.findAll();
    }


}
