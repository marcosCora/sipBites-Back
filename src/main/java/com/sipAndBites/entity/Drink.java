package com.sipAndBites.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String strDrink;
    @NonNull
    private String strCategory;
    private String strAlcoholic;
    private String strGlass;
    private String strInstruction;
    private String strDrinkThumb;
    @ElementCollection
    @CollectionTable(name = "drink_ingredients", joinColumns = @JoinColumn(name = "drink_id"))
    @Column(name = "ingredient")
    private List<String> strIngredients;
    @ElementCollection
    @CollectionTable(name = "drink_ingredients", joinColumns = @JoinColumn(name = "drink_id"))
    @Column(name = "measure")
    private List<String> strMeasure;
    private String strSource;
    private String strImageSource;
    @JsonIgnoreProperties("drinks")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
