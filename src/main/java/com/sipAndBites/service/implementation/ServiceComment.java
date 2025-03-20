package com.sipAndBites.service.implementation;


import com.sipAndBites.entity.Comment;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoComment;
import com.sipAndBites.exception.errror.CommentsNotFound;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import com.sipAndBites.mapper.MapperComment;
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
    @Autowired
    private MapperComment mapper;

    @Override
    public ResponseEntity<?> getCommentsByIdProduct(Long idProduct) throws CommentsNotFound{
        List<Comment> comments = repository.findByProductId(idProduct);
        if(comments.isEmpty()){
            throw new CommentsNotFound("This product has no reviews");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @Override
    public List<Comment> getAllComents() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> saveComment(DtoComment dtoComment) throws ObjectNotFoundException {
        User userComment = serviceUser.getUserById(dtoComment.getIdUser());
        Comment comment = mapper.dtoCommentToComment(dtoComment, userComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comment));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long id) throws ObjectNotFoundException{
        repository.findById(id).orElseThrow(()->
                new ObjectNotFoundException("the comment does not exist"));
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("comment deleted");
    }
}
