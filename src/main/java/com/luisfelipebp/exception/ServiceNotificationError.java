package com.luisfelipebp.exception;

public class ServiceNotificationError extends RuntimeException{

    public ServiceNotificationError(){
        super("Serviço de notificação indisponível");
}
}
