package br.edu.infnet.ReceitaFacil.model;

import java.sql.Date;

import jakarta.persistence.Temporal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.TemporalType;

@Entity
public class Receita {
    @Id
    @GeneratedValue
    private Long idReceita;
    private String nome;
    private String preparo;
    @Temporal(TemporalType.DATE)
    private Date dataReceita;

    public Long getIdReceita() {
        return this.idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreparo() {
        return this.preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public Date getDataReceita() {
        return this.dataReceita;
    }

    public void setDataReceita(Date dataReceita) {
        this.dataReceita = dataReceita;
    }
}
