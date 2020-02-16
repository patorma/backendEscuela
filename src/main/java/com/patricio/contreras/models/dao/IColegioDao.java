package com.patricio.contreras.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.models.repository.Colegio;

public interface IColegioDao extends JpaRepository<Colegio, Long> {

	//falta agregar informacion de profesores
}
