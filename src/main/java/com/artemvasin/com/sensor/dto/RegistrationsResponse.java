package com.artemvasin.com.sensor.dto;


import java.util.List;

public class RegistrationsResponse {

    private List<RegistrationsDTO> registrationDTOList;

    public List<RegistrationsDTO> getRegistrationDTOListList() {
        return registrationDTOList;
    }

    public void setRegistrationList(List<RegistrationsDTO> registrationDTOList) {
        this.registrationDTOList = registrationDTOList;
    }

    public RegistrationsResponse(List<RegistrationsDTO> registrationDTOList) {
        this.registrationDTOList = registrationDTOList;
    }
}
