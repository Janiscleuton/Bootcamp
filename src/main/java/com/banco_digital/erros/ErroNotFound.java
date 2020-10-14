package com.banco_digital.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Classe para envio de menssagem simples de erro NOT FOUND
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErroNotFound extends RuntimeException {
    public ErroNotFound(String mensage) {
        super(mensage);
    }
}
