package com.sipAndBites.repository;

import com.sipAndBites.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryComment extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.idProduct = :idProduct")
    List<Comment> findByProductId(@Param("idProduct") Long idProduct);
}
