package com.banco_digital.modelo.cliente;

import com.banco_digital.modelo.GeradorDeIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TerceiroPasso extends GeradorDeIdEntity {
    @Column(name = "fotoCPF")
    private String fotoCPF;

    public TerceiroPasso(String fotoCPF) {
        this.fotoCPF = fotoCPF;
    }

    //Geters and Seters


    public String getFotoCPF() {
        return fotoCPF;
    }

    public void setFotoCPF(String fotoCPF) {
        this.fotoCPF = fotoCPF;
    }
}
