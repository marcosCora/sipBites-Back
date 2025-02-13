package com.sipAndBites.mapper;

import com.sipAndBites.entity.Meal;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoMeal;
import org.springframework.stereotype.Component;

@Component
public class MapperMeal {

    public Meal DtoMealToMeal(DtoMeal dtoMeal, User user){
        Meal meal = new Meal();
        meal.setId(dtoMeal.getId());
        meal.setStrMeal(dtoMeal.getStrMeal());
        meal.setStrCategory(dtoMeal.getStrCategory());
        meal.setStrArea(dtoMeal.getStrArea());
        meal.setStrInstruction(dtoMeal.getStrInstruction());
        meal.setStrMealThumb(dtoMeal.getStrMealThumb());
        meal.setStrIngredients(dtoMeal.getStrIngredients());
        meal.setStrMeasure(dtoMeal.getStrMeasure());
        meal.setStrSource(dtoMeal.getStrSource());
        meal.setStrImageSource(dtoMeal.getStrImageSource());
        meal.setUser(user);
        return meal;
    }

}
