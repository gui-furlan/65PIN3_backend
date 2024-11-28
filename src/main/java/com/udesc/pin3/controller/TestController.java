package com.udesc.pin3.controller;

import com.udesc.pin3.model.*;
import com.udesc.pin3.repository.CursoRepository;
import com.udesc.pin3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UsuarioRepository usuarioRepo;
    
    @Autowired
    CursoRepository cursoRepo;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Você acessou um endpoint protegido!";
    }
    
    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario saved = usuarioRepo.save(usuario);
        
        
        return saved.toString();
    }
    
    @PostMapping("/createcurso")
    public String register(@RequestBody Curso curso) {
        Curso saved = cursoRepo.save(curso);
        
        return saved.toString();
    }
}