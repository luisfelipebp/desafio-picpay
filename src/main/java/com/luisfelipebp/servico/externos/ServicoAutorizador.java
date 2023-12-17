package com.luisfelipebp.servico.externos;

import com.luisfelipebp.exception.ServiceAutorizationError;
import com.luisfelipebp.modelo.DTO.ServicoTransferenciaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoAutorizador {

    public void autorizarTransferencia(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ServicoTransferenciaDTO> resp = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", ServicoTransferenciaDTO.class);
       if(!resp.getBody().message().equals("Autorizado")){
           throw new ServiceAutorizationError();
       }
    }
}
