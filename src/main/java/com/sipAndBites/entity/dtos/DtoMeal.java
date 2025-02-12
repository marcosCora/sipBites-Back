package com.sipAndBites.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoMeal {
    private Long id;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstruction;
    private String strMealThumb;
    private List<String> strIngredients;
    private List<String> strMeasure;
    private String strSource;
    private String strImageSource;
    private Long idUser;

}
