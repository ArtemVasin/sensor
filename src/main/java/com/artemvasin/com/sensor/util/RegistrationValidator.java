package com.artemvasin.com.sensor.util;

import com.artemvasin.com.sensor.models.Registration;
import com.artemvasin.com.sensor.services.SensorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {
    private final SensorsService sensorsService;

    public RegistrationValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Registration.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Registration registration = (Registration) target;
        if (registration.getSensor() == null) {
            return;
        }
        if (sensorsService.findByName(registration.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем!");
    }
}
