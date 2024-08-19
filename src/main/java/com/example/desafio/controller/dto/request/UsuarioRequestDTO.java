package com.example.desafio.controller.dto.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsuarioRequestDTO implements Serializable {
    private String nome;
    private String email;
}
