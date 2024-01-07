package br.edu.infnet.ReceitaFacil.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Receita;
import br.edu.infnet.ReceitaFacil.service.ReceitaService;

@RestController
@CrossOrigin
public class ReceitaController {
    public ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping("/receita")
    Receita novaReceita(@RequestBody Receita novaReceita) {
        return receitaService.newReceita(novaReceita);
    }

    @PostMapping("/receitas")
    List<Receita> getAllReceitas(@RequestBody String userUID) throws InterruptedException, ExecutionException {
        return receitaService.getReceitas(userUID);
    }

    @GetMapping("/receitasADM")
    List<Receita> getAllReceitas() throws InterruptedException, ExecutionException {
        return receitaService.getReceitas();
    }
}