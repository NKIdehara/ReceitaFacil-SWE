package br.edu.infnet.ReceitaFacil.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;

import br.edu.infnet.ReceitaFacil.model.UsuarioRegistro;
import br.edu.infnet.ReceitaFacil.service.UsuarioService;

@RestController
@CrossOrigin
public class UsuarioController {
    public UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    List<UsuarioRegistro> getAllUsuarios(@RequestBody String userUID) throws InterruptedException, ExecutionException {
        if(usuarioService.existUsuario(userUID))
            return usuarioService.getUsuarios();
        return null;
    }

    @PostMapping("/usuario")
    UsuarioRegistro getUsuario(@RequestBody String userUID) throws InterruptedException, ExecutionException, FirebaseAuthException {
        return usuarioService.getUsuario(userUID);
    }

    @PostMapping("/usuarionovo")
    UsuarioRegistro setUsuario(@RequestBody UsuarioRegistro usuario) throws InterruptedException, ExecutionException, FirebaseAuthException {
        return usuarioService.setUsuario(usuario);
    }

    @DeleteMapping("/apagausuario/{UID}") 
    void apagaUsuario(@PathVariable String UID) {
        try {
            usuarioService.deleteUsuario(UID);
        } catch (FirebaseAuthException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}