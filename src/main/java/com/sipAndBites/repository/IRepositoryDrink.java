package com.sipAndBites.repository;

import com.sipAndBites.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryDrink extends JpaRepository<Drink, Long> {
}
