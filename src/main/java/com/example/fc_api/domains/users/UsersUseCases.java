package com.example.fc_api.domains.users;



import com.example.fc_api.domains.users.entity.UsersEntity;
import com.example.fc_api.domains.users.presentation.UsersDTO;
import com.example.fc_api.domains.users.repository.UsersDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class UsersUseCases {

    private final UsersDataAccess usersDataAccess;

    // create new user
    public UsersDTO createUser(UsersEntity userParam){
    return UsersDTO.builder()
            .name(userParam.getName())
            .active(userParam.getActive())
            .address(userParam.getAddress())
            .build();
    }

    // find any user on dataBase
    public List<UsersDTO> findUserById(UsersDTO usersDTO){

        var usersModel = usersDataAccess.getUserById(usersDTO);
        return usersModel.stream().map(users -> {
             return UsersDTO.builder()
                    .id(users.getId())
                    .name(users.getName())
                    .active(users.isActive())
                    .address(users.getAddressModel())
                    .build();
        }).toList();
    }

    public List<UsersDTO> getUsersList(){

        var usersModel = usersDataAccess.getUsersList();
        return usersModel.stream().map(users -> {
            return UsersDTO.builder()
                    .id(users.getId())
                    .name(users.getName())
                    .active(users.isActive())
                    .address(users.getAddressModel())
                    .build();
        }).toList();
    }
}
