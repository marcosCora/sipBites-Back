package com.sipAndBites.repository;

import com.sipAndBites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryUser extends JpaRepository<User, Long> {
}
