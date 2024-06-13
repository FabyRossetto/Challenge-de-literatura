/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.one.ChalengeLiteratura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Faby
 */
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @Column(unique = true)
    String titulo;
    
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    
    String idioma;
    
    int numeroDeDescarga;

    public Libro() {
    }
   
    
    public Libro(DatosLibros datosLibro){
        this.titulo = datosLibro.titulo();
       if (datosLibro.autores() != null && !datosLibro.autores().isEmpty()) {
            this.autor = new Autor(datosLibro.autores().get(0));
        } else {
            
            this.autor = null;
        }
        if (datosLibro.idiomas() != null && !datosLibro.idiomas().isEmpty()) {
            this.idioma = datosLibro.idiomas().get(0);
        } else {
       
            this.idioma = null;
        }
        this.numeroDeDescarga = datosLibro.numeroDeDescargas();
    }


    @Override
    public String toString() {
        return  
                "titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.toString() : "Sin autor") +
                ", idioma=" + idioma +
                ", numero de descargas='" + numeroDeDescarga + '\''; 

    }

    public Long getId() {
        return Id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getNumeroDeDescarga() {
        return numeroDeDescarga;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setNumeroDeDescarga(int numeroDeDescarga) {
        this.numeroDeDescarga = numeroDeDescarga;
    }
    
    
}
