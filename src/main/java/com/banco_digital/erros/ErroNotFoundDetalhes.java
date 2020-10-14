package com.banco_digital.erros;

// Classe para atribuir os detalhes do builder de erro NOT FOUND(não existe)
public class ErroNotFoundDetalhes extends ErroDetalhes {

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

        //Builder de erro NOT FOUND para implementação na classe ErroDetalhesValidacao
        public ErroNotFoundDetalhes build() {
            ErroNotFoundDetalhes erroNotFoundDetalhes = new ErroNotFoundDetalhes();
            erroNotFoundDetalhes.setTempo(tempo);
            erroNotFoundDetalhes.setTitulo(titulo);
            erroNotFoundDetalhes.setStatus(status);
            erroNotFoundDetalhes.setMensagem(mensagem);
            erroNotFoundDetalhes.setDetalhe(detalhe);
            return erroNotFoundDetalhes;
        }
    }
}
