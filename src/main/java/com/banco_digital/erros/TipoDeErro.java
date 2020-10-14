package com.banco_digital.erros;

public class TipoDeErro {
    private String mensagemDeErro;

    //Classe para mostrar uma menssagem de erro simples
    public TipoDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }

    public String getMensagemDeErro() {
        return mensagemDeErro;
    }
}
