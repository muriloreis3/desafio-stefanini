package com.example.desafio.controller.usuario;

import com.example.desafio.controller.dto.request.UsuarioRequestDTO;
import com.example.desafio.controller.dto.response.UsuarioResponseDTO;
import com.example.desafio.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<UsuarioResponseDTO> findAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO findUsuario(@PathVariable Integer id) {
        var usuario = usuarioService.findUsuarioById(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Inexistente");
        } else {
            return usuario;
        }
    }

    @PostMapping()
    public void saveUsuario(@RequestBody UsuarioRequestDTO requestDTO) {
        usuarioService.saveUsuario(requestDTO);
    }

    @PatchMapping("/{id}")
    public void editarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO requestDTO) {
        try {
            usuarioService.editarUsuario(id, requestDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Inexistente");
        }
    }

    @DeleteMapping("/{id}")
    public UsuarioResponseDTO deleteUsuario(@PathVariable Integer id) {
        var usuario = usuarioService.deleteUsuario(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Inexistente");
        } else {
            return usuario;
        }
    }
}
