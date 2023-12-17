package com.luisfelipebp.controller;

import com.luisfelipebp.modelo.DTO.TransferenciaDTO;
import com.luisfelipebp.modelo.Transferencia;
import com.luisfelipebp.servico.TransferenciaServico;
import com.luisfelipebp.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransferenciaController {


    @Autowired
    private TransferenciaServico transferenciaServico;

    @PostMapping
    public ResponseEntity<Transferencia> realizarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) throws Exception {
        return ResponseEntity.ok().body(transferenciaServico.validarTransferencia(transferenciaDTO));
    }
}
