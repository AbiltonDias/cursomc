package com.abiltondias.cursosmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abiltondias.cursosmc.domain.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{

}
