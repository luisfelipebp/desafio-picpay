package com.luisfelipebp.servico;

import com.luisfelipebp.exception.ServiceAutorizationError;
import com.luisfelipebp.exception.TypeUserException;
import com.luisfelipebp.modelo.DTO.TransferenciaDTO;
import com.luisfelipebp.modelo.DTO.UsuarioDTO;
import com.luisfelipebp.modelo.Transferencia;
import com.luisfelipebp.modelo.Usuario;
import com.luisfelipebp.modelo.enums.TipoUsuarioEnum;
import com.luisfelipebp.repositorio.TransferenciaRepositorio;
import com.luisfelipebp.repositorio.UsuarioRepositorio;
import com.luisfelipebp.servico.externos.ServicoAutorizador;
import com.luisfelipebp.servico.externos.ServicoNotificacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class TransferenciaServicoTest {

    @Mock
    private ServicoAutorizador servicoAutorizador;

    @Mock
    private ServicoNotificacao servicoNotificacao;

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private TransferenciaRepositorio transferenciaRepositorio;

    @Mock
    private UsuarioServico usuarioServico;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @InjectMocks
    private TransferenciaServico transferenciaServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Retornar um objeto Transferencia quando os critérios forem atendidos")
    void retornarTransferenciaQuandoTodoOsCriteriosForemAtendidos() throws Exception {

        UsuarioDTO payerDTO = new UsuarioDTO(1, "Luis Felipe", "12345678910", "luis@gmail.com", "12345", 100.0, TipoUsuarioEnum.COMUM);
        UsuarioDTO payeeDTO = new UsuarioDTO(2, "Joao Carlos", "12345678911", "joao@gmail.com", "12345", 50.0, TipoUsuarioEnum.LOJISTA);
        Usuario payer = new Usuario(payerDTO);
        Usuario payee = new Usuario(payeeDTO);

        when(usuarioServico.findById(1)).thenReturn(payer);
        when(usuarioServico.findById(2)).thenReturn(payee);


        TransferenciaDTO transferencia = new TransferenciaDTO(50.0,  payer.getId(), payee.getId());
        when(usuarioServico.validarUsuario(payer, transferencia.valorTransferencia())).thenReturn(true);
        when(usuarioServico.validarTipoUsuario(any())).thenReturn(true);

        transferenciaServico.validarTransferencia(transferencia);

        verify(transferenciaRepositorio, times(1)).save(any());


        payer.setSaldo(50.0);
        payee.setSaldo(100.0);
        verify(usuarioRepositorio, times(1)).save(payer);
        verify(usuarioRepositorio, times(1)).save(payee);



    }


    @Test
    @DisplayName("Deve lançar uma exceçao quando o usuario tiver o saldo menor que 0 e/ou o saldo for menor que o valor da transferência")
    void lancarExcecaoQuandoPagadorNaoPossuirSaldo() throws Exception{
        UsuarioDTO payerDTO = new UsuarioDTO(1, "Luis Felipe", "12345678910", "luis@gmail.com", "12345", 100.0, TipoUsuarioEnum.COMUM);
        UsuarioDTO payeeDTO = new UsuarioDTO(2, "Joao Carlos", "12345678911", "joao@gmail.com", "12345", 50.0, TipoUsuarioEnum.LOJISTA);
        Usuario payer = new Usuario(payerDTO);
        Usuario payee = new Usuario(payeeDTO);

        when(usuarioServico.findById(1)).thenReturn(payer);
        when(usuarioServico.findById(2)).thenReturn(payee);


        Exception excecaoSaldoInsuficiente = Assertions.assertThrows(Exception.class, () -> {
            TransferenciaDTO transferencia = new TransferenciaDTO(150.0,  payer.getId(), payee.getId());
            when(usuarioServico.validarTipoUsuario(any())).thenReturn(true);
            when(usuarioServico.validarUsuario(payer, transferencia.valorTransferencia())).thenReturn(false);

        });

        Assertions.assertEquals("Usuário não possui saldo suficiente", excecaoSaldoInsuficiente.getMessage());
    }


    @Test
    @DisplayName("Deve ser lançado uma exceçao quando o lojista realizar uma transferência.")
    void lancarExcecaoQuandoLojistaRealizarTransferencia() throws Exception{
        UsuarioDTO payerDTO = new UsuarioDTO(1, "Luis Felipe", "12345678910", "luis@gmail.com", "12345", 100.0, TipoUsuarioEnum.LOJISTA);
        UsuarioDTO payeeDTO = new UsuarioDTO(2, "Joao Carlos", "12345678911", "joao@gmail.com", "12345", 50.0, TipoUsuarioEnum.COMUM);
        Usuario payer = new Usuario(payerDTO);
        Usuario payee = new Usuario(payeeDTO);

        when(usuarioServico.findById(1)).thenReturn(payer);
        when(usuarioServico.findById(2)).thenReturn(payee);


        TypeUserException excecaoLojistaTransferencia = Assertions.assertThrows(TypeUserException.class, () -> {
            TransferenciaDTO transferencia = new TransferenciaDTO(150.0,  payer.getId(), payee.getId());
            when(usuarioServico.validarUsuario(payer, transferencia.valorTransferencia())).thenReturn(true);
            when(usuarioServico.validarTipoUsuario(any())).thenReturn(false);

        });

        Assertions.assertEquals("Lojistas não podem realizar transferências.", excecaoLojistaTransferencia.getMessage());
    }

}




