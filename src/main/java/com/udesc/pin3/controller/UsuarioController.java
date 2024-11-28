/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.controller;

import com.udesc.pin3.model.Estudante;
import com.udesc.pin3.model.dto.CadastroEstudanteRequest;
import com.udesc.pin3.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guilh
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/estudantes")
    public ResponseEntity<Estudante> cadastrarEstudante(@RequestBody CadastroEstudanteRequest request) {
        Estudante estudante = usuarioService.cadastrarEstudante(request);
        return ResponseEntity.ok(estudante);
    }
}