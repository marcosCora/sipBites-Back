package com.sipAndBites.mapper;

import com.sipAndBites.entity.Comment;
import com.sipAndBites.entity.User;
import com.sipAndBites.entity.dtos.DtoComment;

public class MapperComment {

    public Comment dtoCommentToComment(DtoComment dto, User user){
        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setDateComment(dto.getDateComment());
        comment.setUser(user);
        comment.setIdProduct(dto.getIdProduct());
        return comment;
    }

}
