/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udesc.pin3.model.Estudante;

/**
 *
 * @author guilh
 */
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

}
