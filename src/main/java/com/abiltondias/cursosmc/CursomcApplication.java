package com.abiltondias.cursosmc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abiltondias.cursosmc.domain.Categoria;
import com.abiltondias.cursosmc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, " Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		
	}

}
