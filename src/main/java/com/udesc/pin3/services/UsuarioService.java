/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.services;

import com.udesc.pin3.model.TipoUsuario;
import com.udesc.pin3.model.Estudante;
import com.udesc.pin3.model.Curso;
import com.udesc.pin3.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.udesc.pin3.model.Usuario;
import com.udesc.pin3.model.dto.CadastroEstudanteRequest;
import com.udesc.pin3.repository.CursoRepository;
import com.udesc.pin3.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author guilh
 */
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, EstudanteRepository estudanteRepository,
                          CursoRepository cursoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.estudanteRepository = estudanteRepository;
        this.cursoRepository = cursoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Estudante cadastrarEstudante(CadastroEstudanteRequest request) {
        // Criar e salvar o usuário
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setTipoUsuario(TipoUsuario.ESTUDANTE);
        usuario = usuarioRepository.save(usuario);

        // Buscar curso
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        // Criar e salvar o estudante
        Estudante estudante = new Estudante();
        estudante.setUsuario(usuario);
        estudante.setNome(request.getNome());
        estudante.setSobrenome(request.getSobrenome());
        estudante.setCpf(request.getCpf());
        estudante.setAnoInicio(request.getAnoInicio());
        estudante.setSemestreInicio(request.getSemestreInicio());
        estudante.setCurso(curso);

        return estudanteRepository.save(estudante);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getTipoUsuario().name())
                .build();
    }
}
