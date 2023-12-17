package com.luisfelipebp.modelo;

import com.luisfelipebp.modelo.DTO.UsuarioDTO;
import com.luisfelipebp.modelo.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    public Usuario(UsuarioDTO usuarioDTO){
        this.id = usuarioDTO.id();
        this.nome = usuarioDTO.nome();
        this.cpf = usuarioDTO.cpf();
        this.email = usuarioDTO.email();
        this.senha = usuarioDTO.senha();
        this.saldo = usuarioDTO.saldo();
        this.tipo = usuarioDTO.tipo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String senha;

    private double saldo;

    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;
}
