package com.example.fc_api.domains.common.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AddressModel {
    private String address;
    private String contact;
    private String complement;
    private String cep;
    private String city;
    private String state;
}