package com.luisfelipebp.repositorio;

import com.luisfelipebp.modelo.DTO.UsuarioDTO;
import com.luisfelipebp.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    @Override
    Optional<Usuario> findById(Integer integer);
}
