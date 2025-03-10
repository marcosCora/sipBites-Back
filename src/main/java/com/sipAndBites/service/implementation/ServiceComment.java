package com.sipAndBites.service.implementation;


import com.sipAndBites.entity.Comment;
import com.sipAndBites.entity.dtos.DtoComment;
import com.sipAndBites.exception.errror.CommentsNotFound;
import com.sipAndBites.repository.IRepositoryComment;
import com.sipAndBites.service.IServiceComment;
import com.sipAndBites.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceComment implements IServiceComment {

    @Autowired
    private IRepositoryComment repository;
    @Autowired
    private IServiceUser serviceUser;

    @Override
    public ResponseEntity<?> getCommentsByIdProduct(Long idProduct) throws CommentsNotFound{
        List<Comment> comments = repository.findByProductId(idProduct);
        if(comments.isEmpty()){
            throw new CommentsNotFound("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @Override
    public List<Comment> getAllComents() {
        return List.of();
    }

    @Override
    public ResponseEntity<?> saveComment(DtoComment dtoComment) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteComment(Long id) {
        return null;
    }
}
