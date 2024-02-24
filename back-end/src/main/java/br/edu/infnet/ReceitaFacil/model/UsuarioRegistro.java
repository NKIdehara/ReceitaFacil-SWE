
package br.edu.infnet.ReceitaFacil.model;

public class UsuarioRegistro extends Usuario {
    private String nome;
    private Long tipoAcesso;
    private String endereco;

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

    public UsuarioRegistro(String UID, String email, String nome, Long tipoAcesso, String endereco) {
        super.setEmail(email);
        super.setUID(UID);
        this.nome = nome;
        this.tipoAcesso = tipoAcesso;
        this.endereco = endereco;
    }
}