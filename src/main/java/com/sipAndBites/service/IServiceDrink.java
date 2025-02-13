package com.sipAndBites.service;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.entity.dtos.DtoDrink;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceDrink {
    public List<Drink> getAllDrinks() throws ObjectNotFoundException;
    public Optional<Drink> getById(Long id) throws InvalidDataException;
    public ResponseEntity<?> saveAll(@NonNull List<Drink> drinks);
    public ResponseEntity<?> save(@NonNull DtoDrink drink);
    public ResponseEntity<?> update(@NonNull DtoDrink drink) throws ObjectNotFoundException;
    public ResponseEntity<?> delete(@NonNull Long id) throws ObjectNotFoundException;
    public ResponseEntity<?> deleteAll() throws ObjectNotFoundException;
}
