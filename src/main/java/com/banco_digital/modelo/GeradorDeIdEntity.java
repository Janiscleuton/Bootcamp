package com.banco_digital.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

// Classe gera o id automatico para o BD
@MappedSuperclass
public class GeradorDeIdEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeradorDeIdEntity that = (GeradorDeIdEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Geters and Seters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
