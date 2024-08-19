package com.example.desafio.core.service;

import com.example.desafio.controller.dto.request.UsuarioRequestDTO;
import com.example.desafio.controller.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    public UsuarioResponseDTO findUsuarioById(Integer id);

    public List<UsuarioResponseDTO> findAllUsuarios();

    public void saveUsuario(UsuarioRequestDTO requestDTO);

    public UsuarioResponseDTO deleteUsuario(Integer id);

    public void editarUsuario(Integer id, UsuarioRequestDTO requestDTO) throws Exception;
}
