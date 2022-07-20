package com.artemvasin.com.sensor.dto;


import javax.validation.constraints.NotEmpty;

public class SensorsDTO {

    @NotEmpty(message = "Модель датчика не должна быть пустой")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorsDTO(String name) {
        this.name = name;
    }

    public SensorsDTO() {
    }
}
