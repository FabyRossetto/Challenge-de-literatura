/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.one.ChalengeLiteratura.repository;

import com.one.ChalengeLiteratura.model.Libro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Faby
 */
@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(String idioma);

    public Optional<Libro> findByTituloIgnoreCase(String nombreLibro);
}
