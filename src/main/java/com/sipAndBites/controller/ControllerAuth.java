package com.sipAndBites.controller;

import com.sipAndBites.entity.dtos.DtoLoginUser;
import com.sipAndBites.entity.dtos.DtoRegisterUser;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.service.IServiceUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ControllerAuth {

    @Autowired
    private IServiceUser service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DtoLoginUser login) throws ObjectNotFoundException {
        return service.login(login);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody DtoRegisterUser user){
        System.out.println(user);
        return service.registerUser(user);
    }




}
