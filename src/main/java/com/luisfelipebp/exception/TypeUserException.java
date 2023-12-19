package com.luisfelipebp.exception;

public class TypeUserException extends RuntimeException{

    public TypeUserException(){
        super("Lojistas não podem realizar transferências.");
    }
}
