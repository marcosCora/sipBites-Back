package com.sipAndBites.controller;

import com.sipAndBites.entity.Meal;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.service.IServiceMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
public class ControllerMeal {

    @Autowired
    private IServiceMeal service;

    @GetMapping()
    public ResponseEntity<?> getAll() throws ObjectNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllMeals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws InvalidDataException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Meal meal){
        System.out.println("holaa " + meal);
        return service.save(meal);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Meal meal) throws ObjectNotFoundException{
        return service.update(meal);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ObjectNotFoundException{
        return service.delete(id);
    }

}
