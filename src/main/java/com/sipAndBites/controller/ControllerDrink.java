package com.sipAndBites.controller;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.service.IServiceDrink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink")
public class ControllerDrink {

    @Autowired
    private IServiceDrink service;

    @GetMapping()
    public ResponseEntity<?> getAll() throws ObjectNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllDrinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws InvalidDataException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestBody List<Drink> drinks){
        return service.saveAll(drinks);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Drink drink){
        System.out.println(drink);
        return service.save(drink);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Drink drink) throws ObjectNotFoundException{
        return service.update(drink);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ObjectNotFoundException{
        return service.delete(id);
    }

}
