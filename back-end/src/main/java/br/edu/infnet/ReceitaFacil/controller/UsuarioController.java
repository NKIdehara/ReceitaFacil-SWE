package br.edu.infnet.ReceitaFacil.controller;

import java.rmi.server.UID;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;

import br.edu.infnet.ReceitaFacil.model.Usuario;
import br.edu.infnet.ReceitaFacil.service.UsuarioService;

@RestController
@CrossOrigin
public class UsuarioController {
    public UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

     @GetMapping("/usuarios")
    List<Usuario> getAllUsuarios() throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarios();
    }

    @PostMapping("/usuario")
    Usuario getUsuario(@RequestBody String userUID) throws InterruptedException, ExecutionException, FirebaseAuthException {
        return usuarioService.getUsuario(userUID);
    }

    @PostMapping("/novousuario")
    Usuario setUsuario(@RequestBody Usuario usuario) throws InterruptedException, ExecutionException, FirebaseAuthException {
        return usuarioService.setUsuario(usuario);
    }

    @PostMapping("/apagausuario")
    void apagaUsuario(@RequestBody String UID) {
        try {
            usuarioService.deleteUsuario(UID);
        } catch (FirebaseAuthException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

   @GetMapping("/")
    String teste()  {
        return "Conexão OK";
    }
}