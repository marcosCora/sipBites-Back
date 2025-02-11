package com.sipAndBites.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = ("email"))})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String LastName;
    @NonNull
    private Date dateOfBirth;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drink> drinks;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals;
    private List<Long> mealsFavs;
    private List<Long> drinkFavs;
}
