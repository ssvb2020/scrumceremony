package com.sis.scrumceremony.exception;

public class RetrospectiveNotFoundException extends RuntimeException{
    public RetrospectiveNotFoundException(String message){
        super(message);
    }
}