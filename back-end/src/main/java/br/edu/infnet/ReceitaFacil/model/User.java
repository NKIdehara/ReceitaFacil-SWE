package br.edu.infnet.ReceitaFacil.model;

public class User {
    private String UID;
    private String email;
    private String nome;
    private Long tipoAcesso;
    private String endereco;

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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTipoAcesso() {
        return this.tipoAcesso;
    }

    public void setTipoAcesso(Long tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public User() {
    }
    public User(String email, String UID) {
        this.email = email;
        this.UID = UID;
    }
    public User(String UID, String email, String nome, Long tipoAcesso, String endereco) {
        this.UID = UID;
        this.email = email;
        this.nome = nome;
        this.tipoAcesso = tipoAcesso;
        this.endereco = endereco;
    }
}
