package com.luisfelipebp.controller;

import com.luisfelipebp.modelo.DTO.TransferenciaDTO;
import com.luisfelipebp.modelo.DTO.UsuarioDTO;
import com.luisfelipebp.modelo.Usuario;
import com.luisfelipebp.servico.UsuarioServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioServico usuarioServico;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarUsuarios(){
        return ResponseEntity.ok().body(usuarioServico.buscarUsuarios());
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return ResponseEntity.status(201).body(usuarioServico.salvarUsuario(usuarioDTO));
    }


}

