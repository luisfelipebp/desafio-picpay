package com.luisfelipebp.servico;

import com.luisfelipebp.exception.TypeUserException;
import com.luisfelipebp.modelo.DTO.TransferenciaDTO;
import com.luisfelipebp.modelo.DTO.UsuarioDTO;
import com.luisfelipebp.modelo.Usuario;
import com.luisfelipebp.modelo.enums.TipoUsuarioEnum;
import com.luisfelipebp.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Boolean validarUsuario(Usuario usuario, double valorTransferencia) throws Exception {
        if(usuario.getSaldo() < 0 || usuario.getSaldo() < valorTransferencia) return false;
        return true;
    }

    public Boolean validarTipoUsuario(Usuario usuario) throws Exception{
        return !usuario.getTipo().equals(TipoUsuarioEnum.LOJISTA);
    }

    public Usuario findById(Integer id) throws Exception {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public List<Usuario> buscarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public Usuario salvarUsuario(UsuarioDTO usuario){
        Usuario novoUsuario = new Usuario(usuario);
        return usuarioRepositorio.save(novoUsuario);
    }

}
