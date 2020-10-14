package com.banco_digital.erros;

// Classe para atribuir os detalhes do builder de erros gerais
public class ErroDetalhes {
    private String titulo;
    private int status;
    private String detalhe;
    private long tempo;
    private String mensagem;

    public static final class Builder {
        private String titulo;
        private int status;
        private String detalhe;
        private long tempo;
        private String mensagem;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detalhe(String detalhe) {
            this.detalhe = detalhe;
            return this;
        }

        public Builder tempo(long tempo) {
            this.tempo = tempo;
            return this;
        }

        public Builder mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        //Builder da classe ErroDetalhes para implementação na classe ErroDetalhesValidação
        public ErroDetalhes build() {
            ErroDetalhes erroDetalhes = new ErroDetalhes();
            erroDetalhes.setTitulo(titulo);
            erroDetalhes.setStatus(status);
            erroDetalhes.setDetalhe(detalhe);
            erroDetalhes.setTempo(tempo);
            erroDetalhes.setMensagem(mensagem);
            return erroDetalhes;
        }
    }
    // Geters and Seters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
