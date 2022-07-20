package com.artemvasin.com.sensor.controllers;


import com.artemvasin.com.sensor.dto.SensorsDTO;
import com.artemvasin.com.sensor.models.Sensor;
import com.artemvasin.com.sensor.services.SensorsService;
import com.artemvasin.com.sensor.util.RegistrationErrorResponse;
import com.artemvasin.com.sensor.util.RegistrationException;
import com.artemvasin.com.sensor.util.SensorsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.artemvasin.com.sensor.util.ErrorsUtil.returnErrorsToClient;

@RestController

@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsValidator sensorsValidator;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsController(SensorsValidator sensorsValidator, SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorsValidator = sensorsValidator;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    // добавление нового датчика
    @PostMapping("/registration")
    //  @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> saveSensors(@RequestBody @Valid SensorsDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensorAdd = convertToSensor(sensorDTO);
        sensorsValidator.validate(sensorAdd, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        sensorsService.newSensor(sensorAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<RegistrationErrorResponse> handleException(RegistrationException e) {
        RegistrationErrorResponse response = new RegistrationErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorsDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
