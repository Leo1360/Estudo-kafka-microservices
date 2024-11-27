package com.example.servicoestoque.exceptions;

public class ClassNotFoundException extends RuntimeException{
    public ClassNotFoundException(String json) {
        super("No suitable conversion for the object:" + json);
    }


}
