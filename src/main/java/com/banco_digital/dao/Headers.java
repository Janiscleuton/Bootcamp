package com.banco_digital.dao;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;

@ControllerAdvice
public class Headers {
    private String status;

    //Metodo para lançar o header apos o POST do primeiro passo
    @Test
    public ResponseEntity<?> headerUri() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("/segundo_passo")
                .build();
        assertEquals("http://localhost:8080/segundo_passo", uriComponents.toUriString());
        return new ResponseEntity<>(uriComponents, HttpStatus.OK);
    }

    //Metodo para lançar o header apos o POST do segundo passo
    @Test
    public ResponseEntity<?> headerUri2() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("/terceiro_passo")
                .build();
        assertEquals("http://localhost:8080/terceiro_passo", uriComponents.toUriString());
        return new ResponseEntity<>(uriComponents, HttpStatus.OK);
    }

    // Geters and Seters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
