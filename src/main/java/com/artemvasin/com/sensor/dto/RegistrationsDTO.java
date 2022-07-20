package com.artemvasin.com.sensor.dto;


import javax.validation.constraints.*;

public class RegistrationsDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull(message = "Есть дождь или нет ? true/false ?")
    private Boolean raining;

    @NotNull
    private SensorsDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorsDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorsDTO sensor) {
        this.sensor = sensor;
    }

    public RegistrationsDTO(Double value, Boolean raining, SensorsDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }
}
