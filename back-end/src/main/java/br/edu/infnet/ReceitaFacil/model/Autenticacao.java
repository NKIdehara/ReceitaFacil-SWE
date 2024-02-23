package br.edu.infnet.ReceitaFacil.model;

public class Autenticacao {
    private String UID;
    private String email;
    private String senha;

    public String getUID() {
        return this.UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public Autenticacao() {
    }

    public Autenticacao(String UID, String email) {
        this.UID = UID;
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
