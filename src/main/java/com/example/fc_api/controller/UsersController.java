package com.example.fc_api.controller;


import com.example.fc_api.config.ResponseBody;
import com.example.fc_api.config.ResponseBuilder;
import com.example.fc_api.controller.param.UsersPostParam;
import com.example.fc_api.domains.users.UsersUseCases;
import com.example.fc_api.domains.users.entity.UsersEntity;
import com.example.fc_api.domains.users.presentation.UsersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersUseCases usersUseCases;

    public ResponseEntity<ResponseBody<List<UsersDTO>>> findUserById(
            @RequestParam(value = "userId", required = true) UsersDTO userId
    ){
        var responseUser = usersUseCases.findUserById(userId);

        return new ResponseBuilder<List<UsersDTO>>(HttpStatusCode.valueOf(200), responseUser).build();
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseBody<List<UsersDTO>>> findAllUsers(){

        var response = usersUseCases.getUsersList();

        return new ResponseBuilder<List<UsersDTO>>(HttpStatusCode.valueOf(200), response).build();
    }

//    @PostMapping("/insert")
//    public ResponseEntity<ResponseBody<UsersDTO>> createUser(
//            @RequestBody UsersPostParam usersPostParam) {
//
//        UsersEntity usersEntity = UsersEntity.builder()
//                .name(usersPostParam.getName())
//                .address(usersPostParam.getAddress())
//                .active(usersPostParam.getActive())
//                .build();
//
//        var response = usersUseCases.createUser(usersEntity);
//
//        return new ResponseBuilder<UsersDTO>(HttpStatusCode.valueOf(200), response).build();
//    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "API is working!";
    }

}
