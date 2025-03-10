package com.sipAndBites.service;

import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoLoginUser;
import com.sipAndBites.entity.dtos.DtoRegisterUser;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface IServiceUser {

    public ResponseEntity<?> registerUser(DtoRegisterUser register);
    public ResponseEntity<?> login(DtoLoginUser login) throws ObjectNotFoundException;
    public User getUserById(@NonNull Long id) throws ObjectNotFoundException;

}
