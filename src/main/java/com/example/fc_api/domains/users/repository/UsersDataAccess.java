package com.example.fc_api.domains.users.repository;

import com.example.fc_api.custon.exception.ModelViolationException;
import com.example.fc_api.domains.users.model.UsersModel;
import com.example.fc_api.domains.users.presentation.UsersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersDataAccess {

    private final UsersRepository usersRepository;

    public List<UsersModel> getUserById(UsersDTO usersDTO){
        return usersRepository.getUserById(usersDTO.getId()).stream().map(usersEntity -> {
            try{
                return UsersModel.fromEntity(usersEntity);
            }catch (ModelViolationException e){
                throw new IllegalArgumentException(e);
            }
        }).toList();
    }

    public List<UsersModel> getUsersList(){
        return usersRepository.getUsersList().stream().map(usersEntity -> {
            try {
                return UsersModel.fromEntity(usersEntity);
            } catch (ModelViolationException e){
                throw new IllegalArgumentException((e));
            }
        }).toList();
    }
}
