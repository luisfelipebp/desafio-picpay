package com.luisfelipebp.modelo;

import com.luisfelipebp.modelo.DTO.TransferenciaDTO;
import com.luisfelipebp.modelo.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double valorTransferencia;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Usuario payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private Usuario payee;

}
