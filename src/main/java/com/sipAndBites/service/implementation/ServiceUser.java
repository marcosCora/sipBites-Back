package com.sipAndBites.service.implementation;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.entity.Meal;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoLoginUser;
import com.sipAndBites.entity.dtos.DtoRegisterUser;
import com.sipAndBites.entity.dtos.DtoResponseAuth;
import com.sipAndBites.entity.enums.Role;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.repository.IRepositoryUser;
import com.sipAndBites.security.JwtService;
import com.sipAndBites.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceUser implements IServiceUser {

    @Autowired
    private IRepositoryUser repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> login(DtoLoginUser login) throws ObjectNotFoundException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        User user = repository.findUserByEmail(login.getEmail()).get();
        if(user == null){
            throw new ObjectNotFoundException("User Not found");
        }
        DtoResponseAuth userResponse = new DtoResponseAuth(jwtService.getToken(user), user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }


    @Override
    public ResponseEntity<?> registerUser(DtoRegisterUser register){
        System.out.println("user que recibe " + register);
        User user = new User(null, register.getFirstName(), register.getLastName(),
                register.getDateOfBirth(), register.getEmail(), passwordEncoder.encode(register.getPassword()),
                true, new ArrayList<Drink>(), new ArrayList<Meal>(), new ArrayList<Long>(),
                new ArrayList<Long>(), Role.USER);

        System.out.println("user a guardar: " + user);
        User userSave =  repository.save(user);
        DtoResponseAuth response = new DtoResponseAuth(jwtService.getToken(userSave), userSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
