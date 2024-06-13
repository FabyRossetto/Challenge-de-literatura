/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.one.ChalengeLiteratura.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author Faby
 */
@Entity
@Table(name = "autor")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    String nombre;
    
    int anioDeFallecimiento;
    
    int anioDeNacimiento;
    
   @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }
    

    public Autor( DatosAutor autor) {
        this.nombre = autor.nombre();
        this.anioDeFallecimiento = autor.anioDeFallecimiento();
        this.anioDeNacimiento = autor.anioDeNacimiento();
      
    }
    
     @Override
    public String toString() {
        return  
                "nombre del Autor='" + nombre + '\'' +
                ", año de nacimiento=" + anioDeNacimiento +
                ", año de fallecimiento=" + anioDeFallecimiento +
                 '\''; 

    }

    public Long getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioDeFallecimiento() {
        return anioDeFallecimiento;
    }

    public int getAnioDeNacimiento() {
        return anioDeNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnioDeFallecimiento(int anioDeFallecimiento) {
        this.anioDeFallecimiento = anioDeFallecimiento;
    }

    public void setAnioDeNacimiento(int anioDeNacimiento) {
        this.anioDeNacimiento = anioDeNacimiento;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    
    
    
    
}
