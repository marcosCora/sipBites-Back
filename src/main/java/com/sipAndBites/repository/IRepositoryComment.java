package com.sipAndBites.repository;

import com.sipAndBites.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryComment extends JpaRepository<Comment, Long> {
}
