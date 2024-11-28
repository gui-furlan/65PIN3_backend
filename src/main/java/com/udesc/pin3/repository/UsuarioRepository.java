/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udesc.pin3.model.Usuario;
import org.springframework.stereotype.Repository;

/**
 *
 * @author guilh
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsername(String username);
    
}
