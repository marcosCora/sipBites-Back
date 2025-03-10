package com.sipAndBites.service.implementation;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoDrink;
import com.sipAndBites.entity.dtos.DtoExceptionResponse;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.mapper.MapperDrink;
import com.sipAndBites.repository.IRepositoryDrink;
import com.sipAndBites.service.IServiceDrink;
import com.sipAndBites.service.IServiceUser;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceDrink implements IServiceDrink {
    @Autowired
    private IRepositoryDrink repository;
    @Autowired
    private MapperDrink mapperDrink;
    @Autowired
    private IServiceUser serviceUser;

    @Override
    public List<Drink> getAllDrinks() throws ObjectNotFoundException {
        List<Drink> drinks = repository.findAll();
        if(drinks.isEmpty()){
            throw new ObjectNotFoundException("non-existent objects");
        }
        return drinks;
    }

    @Override
    public Optional<Drink> getById(Long id) throws InvalidDataException {
        Optional<Drink> drink = repository.findById(id);
        if(drink.isEmpty()){
            throw new InvalidDataException("The object with the received id is not found");
        }
        return drink;
    }

    @Override
    public ResponseEntity<?> saveAll(@NonNull List<DtoDrink> dtoDrinks) throws ObjectNotFoundException{

        List<Drink> drinks = new ArrayList<Drink>();
        for(DtoDrink dto : dtoDrinks){
            User user = serviceUser.getUserById(dto.getIdUser());
            Drink drink = mapperDrink.DtoDrinkToDrink(dto, user);
            drinks.add(drink);
        }
        repository.saveAll(drinks);
        return ResponseEntity.status(HttpStatus.CREATED).body("Todo ok");
    }

    @Override
    public ResponseEntity<?> save(@NonNull DtoDrink drink) throws ObjectNotFoundException{
        User user = serviceUser.getUserById(drink.getIdUser());
        Drink drinkR = mapperDrink.DtoDrinkToDrink(drink, user);

        try{
            drinkR = repository.save(drinkR);
        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DtoExceptionResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoExceptionResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE));

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(drinkR);

    }

    @Override
    public ResponseEntity<?> update(@NonNull DtoDrink drink) throws ObjectNotFoundException {
        repository.findById(drink.getId())
                .orElseThrow(()-> new ObjectNotFoundException("The object to be modified does not exist"));
        return this.save(drink);
    }

    @Override
    public ResponseEntity<?> delete(@NonNull Long id) throws ObjectNotFoundException {
        repository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("The object to be deleted does not exist"));
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoExceptionResponse("deleted object", HttpStatus.OK)) ;
    }

    @Override
    public ResponseEntity<?> deleteAll() throws ObjectNotFoundException {
        repository.deleteAll();
        return ResponseEntity.ok("deleted all");
    }
}
