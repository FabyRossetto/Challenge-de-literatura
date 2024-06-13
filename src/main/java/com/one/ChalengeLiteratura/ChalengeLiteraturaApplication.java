package com.one.ChalengeLiteratura;

import com.one.ChalengeLiteratura.principal.Principal;
import com.one.ChalengeLiteratura.repository.AutorRepository;
import com.one.ChalengeLiteratura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChalengeLiteraturaApplication implements CommandLineRunner {

    @Autowired
    private LibrosRepository repository;
    
    @Autowired
    private AutorRepository ar;

    public static void main(String[] args) {
        SpringApplication.run(ChalengeLiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository,ar);
        principal.muestraElMenu();

    }
}
