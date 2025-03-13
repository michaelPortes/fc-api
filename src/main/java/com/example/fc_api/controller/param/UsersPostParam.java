package com.example.fc_api.controller.param;

import com.example.fc_api.domains.common.model.AddressModel;
import lombok.Getter;

@Getter
public class UsersPostParam {

    private String name;
    private Boolean active;
    private AddressModel address;
}
