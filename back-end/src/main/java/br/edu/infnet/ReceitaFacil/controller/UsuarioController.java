package br.edu.infnet.ReceitaFacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ReceitaFacil.model.Usuario;
import br.edu.infnet.ReceitaFacil.repository.UsuarioRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    Usuario novoUsuario(@RequestBody Usuario novoUsuario) {
        return usuarioRepository.save(novoUsuario);
    }

    @GetMapping("/usuarios")
    List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
    
}
