package com.example.fc_api.domains.users.presentation;

import com.example.fc_api.domains.common.model.AddressModel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsersDTO {
    private Long id;
    private String name;
    private Boolean active;
    private AddressModel address;
}

