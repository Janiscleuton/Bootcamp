package com.banco_digital.erros.handler;

import com.banco_digital.erros.ErroDetalhes;
import com.banco_digital.erros.ErroDetalhesValidacao;
import com.banco_digital.erros.ErroNotFound;
import com.banco_digital.erros.ErroNotFoundDetalhes;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//Classe onde esta implementado alguns metodos de tipos de erros
@ControllerAdvice
public class HendlerException extends ResponseEntityExceptionHandler {

    //metodo sera chamado quando for solicitado algo na aplicação que não existe no BD
    @ExceptionHandler(ErroNotFound.class)
    public ResponseEntity<?> handleErroNotFound(ErroNotFound enfException) {
        ErroNotFoundDetalhes enfdetalhes = ErroNotFoundDetalhes.Builder
                .newBuilder()
                .tempo(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .titulo("Exceçao NOT FOUND")
                .detalhe(enfException.getMessage())
                .mensagem(enfException.getClass().getName())
                .build();
        return new ResponseEntity<>(enfdetalhes, HttpStatus.NOT_FOUND);
    }

    //metodo sera chamado quando for informado em um campo um tipo não permitido para aquele campo
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErro = manve.getBindingResult().getFieldErrors();
        String campos = fieldErro.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String campoMenssagenm = fieldErro.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ErroDetalhesValidacao enfdetalhes = ErroDetalhesValidacao.Builder
                .newBuilder()
                .tempo(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo("Erro ao validar campo")
                .detalhe("Erro ao validar campo")
                .mensagem(manve.getClass().getName())
                .campo(campos)
                .campoMenssagem(campoMenssagenm)
                .build();
        return new ResponseEntity<>(enfdetalhes, HttpStatus.BAD_REQUEST);
    }

    //metodo sera chamado quando for informado em um campo duplicidade ou algo semelhante
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException cve) {
        ErroDetalhesValidacao erroDetalhesValidacao = ErroDetalhesValidacao.Builder
                .newBuilder()
                .tempo(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo("Erro de Validação de campo")
                .detalhe("Erro de Validação de campo")
                .mensagem(cve.getClass().getName())
                .campo(cve.getConstraintName())
                .campoMenssagem("O Campo " + cve.getConstraintName() + " foi violado")
                .build();
        return new ResponseEntity<>(erroDetalhesValidacao, HttpStatus.BAD_REQUEST);
    }

    //metodo sera chamado quando tiver um erro de outras causas interna na aplicação
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception hei, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErroDetalhes erroDetalhes = ErroDetalhes.Builder
                .newBuilder()
                .tempo(new Date().getTime())
                .status(status.value())
                .titulo("Erro de exception interna")
                .detalhe("Erro de exception interna")
                .mensagem(hei.getClass().getName())
                .build();
        return new ResponseEntity<>(erroDetalhes, headers, status);
    }
}
