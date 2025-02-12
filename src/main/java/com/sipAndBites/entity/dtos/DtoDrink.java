package com.sipAndBites.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoDrink {

    private Long id;
    private String strDrink;
    private String strCategory;
    private String strAlcoholic;
    private String strGlass;
    private String strInstruction;
    private String strDrinkThumb;
    private List<String> strIngredients;
    private List<String> strMeasure;
    private String strSource;
    private String strImageSource;
    private Long idUser;
}
