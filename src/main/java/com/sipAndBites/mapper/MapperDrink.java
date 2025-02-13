package com.sipAndBites.mapper;

import com.sipAndBites.entity.Drink;
import com.sipAndBites.entity.Meal;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoDrink;
import org.springframework.stereotype.Component;

@Component
public class MapperDrink {

    public Drink DtoDrinkToDrink(DtoDrink dtoDrink, User user){
        Drink drink = new Drink();
        drink.setId(dtoDrink.getId());
        drink.setStrDrink(dtoDrink.getStrDrink());
        drink.setStrCategory(dtoDrink.getStrCategory());
        drink.setStrAlcoholic(dtoDrink.getStrAlcoholic());
        drink.setStrGlass(dtoDrink.getStrGlass());
        drink.setStrInstruction(dtoDrink.getStrInstruction());
        drink.setStrDrinkThumb(dtoDrink.getStrDrinkThumb());
        drink.setStrIngredients(dtoDrink.getStrIngredients());
        drink.setStrMeasure(dtoDrink.getStrMeasure());
        drink.setStrSource(dtoDrink.getStrSource());
        drink.setStrImageSource(dtoDrink.getStrImageSource());
        drink.setUser(user);
        return drink;
    }

}
