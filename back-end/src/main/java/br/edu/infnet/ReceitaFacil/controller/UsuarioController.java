package br.edu.infnet.ReceitaFacil.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Usuario;
import br.edu.infnet.ReceitaFacil.model.Usuario;
import br.edu.infnet.ReceitaFacil.service.UsuarioService;

@RestController
// @CrossOrigin("http://localhost:3000")
@CrossOrigin("receitafacil-frontend.azurewebsites.net")
public class UsuarioController {
    public UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // @PostMapping("/usuario")
    // Usuario novoUsuario(@RequestBody Usuario novoUsuario) {
    //     return usuarioRepository.save(novoUsuario);
    // }

    @GetMapping("/usuarios")
    List<Usuario> getAllUsuariosFB() throws InterruptedException, ExecutionException {        
        return usuarioService.getUsuarios();
    }    
}