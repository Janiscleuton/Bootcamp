package com.banco_digital.modelo.cliente;

import com.banco_digital.modelo.GeradorDeIdEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class PrimeiroPasso extends GeradorDeIdEntity {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String sobrenome;
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    @Column(unique = true)
    private String cpf;
    @NotNull
    private Date dataNascimento;

    public PrimeiroPasso() {
    }

    public PrimeiroPasso(String nome, String sobrenome, String email, String cpf, Date dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Geters and Seters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
