package com.sipAndBites.repository;

import com.sipAndBites.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryMeal extends JpaRepository<Meal, Long> {
}
