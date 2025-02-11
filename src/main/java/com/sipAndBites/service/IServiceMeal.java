package com.sipAndBites.service;

import com.sipAndBites.entity.Meal;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceMeal {
    public List<Meal> getAllMeals() throws ObjectNotFoundException;
    public Optional<Meal> getById(Long id) throws InvalidDataException;
    public ResponseEntity<?> saveAll(@NonNull List<Meal> meals);
    public ResponseEntity<?> save(@NonNull Meal meal);
    public ResponseEntity<?> update(@NonNull Meal meal) throws ObjectNotFoundException;
    public ResponseEntity<?> delete(@NonNull Long id) throws ObjectNotFoundException;
}
