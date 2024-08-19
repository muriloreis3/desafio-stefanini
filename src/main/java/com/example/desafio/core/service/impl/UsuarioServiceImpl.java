package com.example.desafio.core.service.impl;

import com.example.desafio.controller.dto.request.UsuarioRequestDTO;
import com.example.desafio.controller.dto.response.UsuarioResponseDTO;
import com.example.desafio.core.domain.UsuarioEntity;
import com.example.desafio.core.repository.UsuarioRepository;
import com.example.desafio.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO findUsuarioById(Integer id) {
        var usuarioEntity = usuarioRepository.findById(id).orElse(null);

        return usuarioEntity == null ? null : UsuarioResponseDTO.builder()
                .dataCadastro(usuarioEntity.getDataCadastro())
                .email(usuarioEntity.getEmail())
                .nome(usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();
    }

    @Override
    public List<UsuarioResponseDTO> findAllUsuarios() {
        var usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioEntity -> UsuarioResponseDTO.builder()
                        .dataCadastro(usuarioEntity.getDataCadastro())
                        .email(usuarioEntity.getEmail())
                        .nome(usuarioEntity.getNome())
                        .id(usuarioEntity.getId())
                        .build())
                .toList();
    }

    @Override
    public void saveUsuario(UsuarioRequestDTO requestDTO) {
        usuarioRepository.save(UsuarioEntity.builder()
                .email(requestDTO.getEmail().isEmpty() || requestDTO.getEmail().isBlank() ? null : requestDTO.getEmail())
                .nome(requestDTO.getNome())
                .dataCadastro(LocalDate.now())
                .build());
    }

    @Override
    public UsuarioResponseDTO deleteUsuario(Integer id) {
        var usuario = findUsuarioById(id);
        usuarioRepository.delete(UsuarioEntity.builder().id(id).build());
        return usuario;
    }

    @Override
    public void editarUsuario(Integer id, UsuarioRequestDTO requestDTO) throws Exception {
        var usuario = findUsuarioById(id);
        if(usuario == null) {
            throw new Exception("Usuario Inexistente");
        } else {
            usuarioRepository.save(UsuarioEntity.builder()
                    .email(requestDTO.getEmail().isEmpty() || requestDTO.getEmail().isBlank() ? null : requestDTO.getEmail())
                    .nome(requestDTO.getNome())
                    .id(id)
                    .dataCadastro(usuario.getDataCadastro())
                    .build());
        }
    }
}
