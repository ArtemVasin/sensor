package com.artemvasin.com.sensor.controllers;


import com.artemvasin.com.sensor.dto.RegistrationsDTO;
import com.artemvasin.com.sensor.dto.RegistrationsResponse;
import com.artemvasin.com.sensor.models.Registration;
import com.artemvasin.com.sensor.services.RegistrationsService;
import com.artemvasin.com.sensor.services.SensorsService;
import com.artemvasin.com.sensor.util.RegistrationErrorResponse;
import com.artemvasin.com.sensor.util.RegistrationException;
import com.artemvasin.com.sensor.util.RegistrationValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.artemvasin.com.sensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/registrations")
public class RegistrationsController {

    private RegistrationsService registrationsService;
    private RegistrationValidator registrationValidator;
    private ModelMapper modelMapper;

    @Autowired
    public RegistrationsController(RegistrationsService registrationsService, SensorsService sensorsService, RegistrationValidator registrationValidator, ModelMapper modelMapper) {
        this.registrationsService = registrationsService;
        this.registrationValidator = registrationValidator;
        this.modelMapper = modelMapper;
    }

    // добавление нового показания
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid RegistrationsDTO registrationsDTO,
                                          BindingResult bindingResult) {
        Registration registrationAdd = convertToRegistration(registrationsDTO);

        registrationValidator.validate(registrationAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        registrationsService.newRegistrations(registrationAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public RegistrationsResponse getRegistrations() {
        return new RegistrationsResponse(registrationsService.registrationList().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    // кол-во зафиксированных дождей
    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return registrationsService.registrationList().stream().filter(Registration::isRaining).count();
    }

    // Перевод объект DTO в модель
    private Registration convertToRegistration(RegistrationsDTO registrationsDTO) {
        return modelMapper.map(registrationsDTO, Registration.class);
    }

    private RegistrationsDTO convertToMeasurementDTO(Registration registration) {
        return modelMapper.map(registration, RegistrationsDTO.class);
    }

    // Исключение
    @ExceptionHandler
    private ResponseEntity<RegistrationErrorResponse> handleException(RegistrationException e) {
        RegistrationErrorResponse response = new RegistrationErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

