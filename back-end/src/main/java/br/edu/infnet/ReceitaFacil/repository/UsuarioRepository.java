package br.edu.infnet.ReceitaFacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.ReceitaFacil.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
