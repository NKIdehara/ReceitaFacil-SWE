package br.edu.infnet.ReceitaFacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.ReceitaFacil.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
}
