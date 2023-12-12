package com.youcode.aftas.handler.exception;

import org.springframework.http.HttpStatus;
public class AlreadyExistsException  extends RuntimeException {

    public String getError() {
        return "This Record Already Exist";
    }
    public HttpStatus getCode(){
        return HttpStatus.FOUND;
    }

}
