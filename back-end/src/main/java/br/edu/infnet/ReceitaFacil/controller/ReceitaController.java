package br.edu.infnet.ReceitaFacil.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Receita;
import br.edu.infnet.ReceitaFacil.service.ReceitaService;
import br.edu.infnet.ReceitaFacil.service.UsuarioService;

@RestController
@CrossOrigin
public class ReceitaController {
    public ReceitaService receitaService;
    public UsuarioService usuarioService;


    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping("/receitanova")
    Receita novaReceita(@RequestBody Receita novaReceita) {
        return receitaService.newReceita(novaReceita);
    }

    @PostMapping("/receitasusuario")
    List<Receita> getReceitasUsuarios(@RequestBody String userUID) throws InterruptedException, ExecutionException {
        return receitaService.getReceitas(userUID);
    }

    @GetMapping("/receitas")
    List<Receita> getReceitas() throws InterruptedException, ExecutionException {
        return receitaService.getReceitas();
    }

    @DeleteMapping("/apagareceita/{id}") 
    void apagaReceita(@PathVariable String id) {
        receitaService.deleteReceita(id);
    }
}