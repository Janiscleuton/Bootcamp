package com.banco_digital.erros;

//Classe onde Valida e detalha os Erros da aplicação
public class ErroDetalhesValidacao extends ErroDetalhes {
    private String campo;
    private String campoMenssagem;

    public static final class Builder {
        private String titulo;
        private int status;
        private String detalhe;
        private long tempo;
        private String mensagem;
        private String campo;
        private String campoMenssagem;

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

        public Builder campo(String campo) {
            this.campo = campo;
            return this;
        }

        public Builder campoMenssagem(String campoMenssagem) {
            this.campoMenssagem = campoMenssagem;
            return this;
        }

        //Build da classe pronto para ser usado nos metodos da classe HrndlerException
        public ErroDetalhesValidacao build() {
            ErroDetalhesValidacao erroDetalhesValidacao = new ErroDetalhesValidacao();
            erroDetalhesValidacao.setTempo(tempo);
            erroDetalhesValidacao.setTitulo(titulo);
            erroDetalhesValidacao.setStatus(status);
            erroDetalhesValidacao.setMensagem(mensagem);
            erroDetalhesValidacao.setDetalhe(detalhe);
            erroDetalhesValidacao.campo = campo;
            erroDetalhesValidacao.campoMenssagem = campoMenssagem;
            return erroDetalhesValidacao;
        }
    }
// Geters and Seters

    public String getCampo() {
        return campo;
    }

    public String getCampoMenssagem() {
        return campoMenssagem;
    }
}
