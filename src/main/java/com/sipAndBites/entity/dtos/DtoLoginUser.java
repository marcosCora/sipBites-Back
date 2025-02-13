package com.sipAndBites.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoLoginUser {

    @NotNull
    private String email;
    @NotNull
    private String password;
}
