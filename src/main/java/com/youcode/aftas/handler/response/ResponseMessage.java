package com.youcode.aftas.handler.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage {
    private final String message;
    private final Object data;

    private ResponseMessage(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity ok(String message, Object data) {
        return ResponseEntity.ok(new ResponseMessage(message, data));
    }

    public static ResponseEntity created(String message, Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message, data));
    }

    public static ResponseEntity notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message, null));
    }

    public static ResponseEntity badRequest(String message) {
        return ResponseEntity.badRequest().body(new ResponseMessage(message, null));
    }

}