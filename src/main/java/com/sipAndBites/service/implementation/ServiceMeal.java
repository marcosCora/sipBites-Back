package com.sipAndBites.service.implementation;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.entity.Meal;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoDrink;
import com.sipAndBites.entity.dtos.DtoMeal;
import com.sipAndBites.entity.dtos.DtoExceptionResponse;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.mapper.MapperMeal;
import com.sipAndBites.repository.IRepositoryMeal;
import com.sipAndBites.repository.IRepositoryUser;
import com.sipAndBites.service.IServiceMeal;
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
public class ServiceMeal implements IServiceMeal {

    @Autowired
    private IRepositoryMeal repository;
    @Autowired
    private MapperMeal mapperMeal;
    @Autowired
    private IServiceUser serviceUser;

    @Override
    public List<Meal> getAllMeals()throws ObjectNotFoundException {
        List<Meal> meals = repository.findAll();
        if(meals.isEmpty()){
            throw new ObjectNotFoundException("non-existent objects");
        }
        return meals;
    }

    @Override
    public Optional<Meal> getById(Long id) throws InvalidDataException {
        Optional<Meal> meal = repository.findById(id);
        if(meal.isEmpty()){
            throw new InvalidDataException("The object with the received id is not found");
        }
        return meal;
    }

    @Override
    public ResponseEntity<?> saveAll(@NonNull List<DtoMeal> dtoMeals)throws ObjectNotFoundException {
        List<Meal> meals = new ArrayList<Meal>();
        for(DtoMeal dto : dtoMeals){
            User user = serviceUser.getUserById(dto.getIdUser());
            Meal meal = mapperMeal.DtoMealToMeal(dto, user);
            meals.add(meal);
        }
        repository.saveAll(meals);
        return ResponseEntity.status(HttpStatus.CREATED).body("Todo ok");
    }

    @Override
    public ResponseEntity<?> save(@NonNull DtoMeal meal) throws ObjectNotFoundException{
        User user = serviceUser.getUserById(meal.getIdUser());
        Meal mealR = mapperMeal.DtoMealToMeal(meal, user);
        try{
            mealR = repository.save(mealR);
        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DtoExceptionResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoExceptionResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE));

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mealR);
    }

    @Override
    public ResponseEntity<?> update(@NonNull DtoMeal meal) throws ObjectNotFoundException{
        repository.findById(meal.getId())
                .orElseThrow(()-> new ObjectNotFoundException("The object to be modified does not exist"));
        return this.save(meal);
    }

    @Override
    public ResponseEntity<?> delete(@NonNull Long id) throws ObjectNotFoundException{
       repository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("The object to be deleted does not exist"));
       repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new DtoExceptionResponse("deleted object", HttpStatus.OK)) ;
    }

    @Override
    public ResponseEntity<?> deleteAll() throws ObjectNotFoundException {
        repository.deleteAll();
        return ResponseEntity.ok("Deleted all");
    }
}
