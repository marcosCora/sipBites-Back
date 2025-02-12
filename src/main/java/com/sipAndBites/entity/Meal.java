package com.sipAndBites.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String strMeal;
    @NonNull
    private String strCategory;
    private String strArea;
    private String strInstruction;
    private String strMealThumb;
    @ElementCollection
    @CollectionTable(name = "meal_ingredients", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "ingredient")
    private List<String> strIngredients;
    @ElementCollection
    @CollectionTable(name = "meal_measures", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "measure")
    private List<String> strMeasure;
    private String strSource;
    private String strImageSource;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
