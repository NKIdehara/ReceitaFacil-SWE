package br.edu.infnet.ReceitaFacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Receita;
import br.edu.infnet.ReceitaFacil.repository.ReceitaRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class ReceitaController {
    @Autowired
    private ReceitaRepository receitaRepository;

    @PostMapping("/receita")
    Receita novaReceita(@RequestBody Receita novaReceita) {
        return receitaRepository.save(novaReceita);
    }

    @GetMapping("/receitas")
    List<Receita> getAllReceitas() {
        return receitaRepository.findAll();
    }
    
}
