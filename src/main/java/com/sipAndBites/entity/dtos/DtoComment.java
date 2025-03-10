package com.sipAndBites.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoComment {
    private Long id;
    @NonNull
    private Long idProduct;
    Long idUser;
    @NonNull
    private String comment;
    private Date dateComment;
}
