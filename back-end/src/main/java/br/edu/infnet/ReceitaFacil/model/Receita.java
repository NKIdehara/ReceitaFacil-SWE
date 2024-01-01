package br.edu.infnet.ReceitaFacil.model;

import java.sql.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Receita {
    private String id;
    private String nome;
    private String ingredientes;
    private String preparo;
    @Temporal(TemporalType.DATE)
    private Date dataReceita;
    private String usuario;
    private String figura;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFigura() {
        return this.figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public Receita() {
    }

    public Receita(String id, String nome, String ingredientes, String preparo, Date dataReceita, String usuario, String figura) {
         this.id = id;
         this.nome = nome;
         this.ingredientes = ingredientes;
         this.preparo = preparo;
         this.dataReceita = dataReceita;
         this.usuario = usuario;
         this.figura = figura;
    }
}