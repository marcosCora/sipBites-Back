package com.sipAndBites.service.implementation;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.entity.dtos.DtoResponse;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.repository.IRepositoryDrink;
import com.sipAndBites.service.IServiceDrink;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDrink implements IServiceDrink {
    @Autowired
    private IRepositoryDrink repository;

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
    public ResponseEntity<?> save(@NonNull Drink drink) {
        Drink drinkR = new Drink();
        try{
            drinkR = repository.save(drink);
        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DtoResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE));

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(drinkR);

    }

    @Override
    public ResponseEntity<?> update(@NonNull Drink drink) throws ObjectNotFoundException {
        repository.findById(drink.getId())
                .orElseThrow(()-> new ObjectNotFoundException("The object to be modified does not exist"));
        return this.save(drink);
    }

    @Override
    public ResponseEntity<?> delete(@NonNull Long id) throws ObjectNotFoundException {
        repository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("The object to be deleted does not exist"));
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponse("deleted object", HttpStatus.OK)) ;
    }
}
