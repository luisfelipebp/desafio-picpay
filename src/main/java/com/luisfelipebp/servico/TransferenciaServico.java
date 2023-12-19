package com.luisfelipebp.servico;

import com.luisfelipebp.exception.TypeUserException;
import com.luisfelipebp.modelo.DTO.TransferenciaDTO;
import com.luisfelipebp.modelo.Transferencia;
import com.luisfelipebp.modelo.Usuario;
import com.luisfelipebp.repositorio.TransferenciaRepositorio;
import com.luisfelipebp.repositorio.UsuarioRepositorio;
import com.luisfelipebp.servico.externos.ServicoNotificacao;
import com.luisfelipebp.servico.externos.ServicoAutorizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaServico {

    @Autowired
    private ServicoAutorizador servicoAutorizador;

    @Autowired
    private ServicoNotificacao servicoNotificacao;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TransferenciaRepositorio transferenciaRepositorio;

    @Autowired
    private UsuarioServico usuarioServico;


    public Transferencia validarTransferencia(TransferenciaDTO transferenciaDTO) throws Exception{
        Usuario payee = usuarioServico.findById(transferenciaDTO.payee_id());
        Usuario payer = usuarioServico.findById(transferenciaDTO.payer_id());

        Boolean usuarioValido = usuarioServico.validarUsuario(payer, transferenciaDTO.valorTransferencia());
        Boolean tipoUsuarioValido = usuarioServico.validarTipoUsuario(payer);

        if(!tipoUsuarioValido){
            throw new TypeUserException();
        }

        if(!usuarioValido){
            throw new Exception("Usuário não possui saldo suficiente");
        }

        Transferencia transferencia = new Transferencia();
        transferencia.setValorTransferencia(transferenciaDTO.valorTransferencia());
        transferencia.setPayer(payer);
        transferencia.setPayee(payee);


        servicoAutorizador.autorizarTransferencia();

        payer.setSaldo(payer.getSaldo() - transferencia.getValorTransferencia());
        payee.setSaldo(payee.getSaldo() + transferencia.getValorTransferencia());
        usuarioRepositorio.save(payer);
        usuarioRepositorio.save(payee);
        transferenciaRepositorio.save(transferencia);

        servicoNotificacao.notificarTransferencia(payer);
        servicoNotificacao.notificarTransferencia(payee);

    return transferencia;
    }


}
