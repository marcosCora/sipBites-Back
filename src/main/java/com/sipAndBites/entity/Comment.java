package com.sipAndBites.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long idProducto;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String comment;
    private Date dateComment;
}
