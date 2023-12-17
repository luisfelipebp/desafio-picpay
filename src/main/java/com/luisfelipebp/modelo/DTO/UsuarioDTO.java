package com.luisfelipebp.modelo.DTO;

import com.luisfelipebp.modelo.enums.TipoUsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(Integer id, @NotEmpty String nome, @NotEmpty @Size(min = 11, max = 11) String cpf, @NotEmpty @Email String email, @NotEmpty String senha,@Min(value = 0) double saldo, TipoUsuarioEnum tipo){
}
