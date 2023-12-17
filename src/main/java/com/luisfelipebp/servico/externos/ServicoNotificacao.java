package com.luisfelipebp.servico.externos;

import com.luisfelipebp.exception.ServiceNotificationError;
import com.luisfelipebp.modelo.DTO.NotificacaoTransferenciaDTO;
import com.luisfelipebp.modelo.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoNotificacao {


    public void notificarTransferencia(Usuario user){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificacaoTransferenciaDTO> resp = restTemplate.getForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", NotificacaoTransferenciaDTO.class);
        if(resp.getBody().message()){
            // Metodo de enviar email
            System.out.println("Notificação enviada para: " + user.getEmail());
        }
        else {
            throw new ServiceNotificationError();
        }
    }
}
