package br.edu.infnet.ReceitaFacil.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GeneralController {
   @GetMapping("/")
    String teste()  {
        return "Conexão OK";
    }
}