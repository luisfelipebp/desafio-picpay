package com.luisfelipebp.exception;

public class ServiceAutorizationError extends RuntimeException{

    public ServiceAutorizationError(){
        super("Serviço de autorização indisponível");
    }
}
