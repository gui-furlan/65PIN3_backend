/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udesc.pin3.repository;

import com.udesc.pin3.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author guilh
 */
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
