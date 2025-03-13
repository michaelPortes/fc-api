package com.example.fc_api.domains.users.model;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.common.model.AddressModel;
import com.example.fc_api.domains.users.entity.UsersEntity;
import lombok.Builder;
import lombok.Getter;

@Builder(builderClassName = "WorkforceModelBuilder", toBuilder = true)
@Getter
public class UsersModel {
    private  Long id;
    private String name;
    private boolean active;
    private AddressModel addressModel;

    public static class WorkforceModelBuilder {
        public UsersModel build()  {
            var userModel = new UsersModel(id, name, active, addressModel);
            validate(userModel);
            return userModel;
        }
    }

    private static void validate(UsersModel userModel){
        // Implement validate;
    }

    public static UsersModel fromEntity(UsersEntity usersEntity) throws ModelViolationException {
        return UsersModel.builder()
                .id(usersEntity.getId())
                .name(usersEntity.getName())
                .active(usersEntity.getActive())
                .addressModel(usersEntity.getAddress())
                .build();
    }
}
