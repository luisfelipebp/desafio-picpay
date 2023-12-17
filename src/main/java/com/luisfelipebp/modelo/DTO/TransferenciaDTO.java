package com.luisfelipebp.modelo.DTO;

import com.luisfelipebp.modelo.Usuario;

public record TransferenciaDTO(double valorTransferencia, Integer payer_id, Integer payee_id) {
}
