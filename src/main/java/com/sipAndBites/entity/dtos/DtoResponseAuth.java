package com.sipAndBites.entity.dtos;

import com.sipAndBites.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoResponseAuth {

    @NonNull
    private String token;
    @NonNull
    private User user;

}
