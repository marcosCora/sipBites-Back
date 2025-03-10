package com.sipAndBites.service;

import com.sipAndBites.entity.Comment;
import com.sipAndBites.entity.dtos.DtoComment;
import com.sipAndBites.exception.errror.CommentsNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceComment {

    public ResponseEntity<?> getCommentsByIdProduct(Long idProduct) throws CommentsNotFound;
    public List<Comment> getAllComents();
    public ResponseEntity<?> saveComment(DtoComment dtoComment);
    public ResponseEntity<?> deleteComment(Long id);
}
