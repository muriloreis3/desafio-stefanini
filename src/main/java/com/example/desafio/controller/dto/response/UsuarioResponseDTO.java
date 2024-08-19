package com.example.desafio.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class UsuarioResponseDTO implements Serializable {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataCadastro;
}
