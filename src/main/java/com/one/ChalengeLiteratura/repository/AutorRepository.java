/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.one.ChalengeLiteratura.repository;

import com.one.ChalengeLiteratura.model.Autor;
import com.one.ChalengeLiteratura.model.Libro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Faby
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor,Long>{
    @Query("SELECT a FROM Autor a")
    List<Autor> findAllAutores();
    
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioDeNacimiento <= :fin AND (a.anioDeFallecimiento >= :inicio OR a.anioDeFallecimiento IS NULL)")
    List<Autor> findAutoresVivosEntreAnios(int inicio, int fin);
}