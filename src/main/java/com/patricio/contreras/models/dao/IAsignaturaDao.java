package com.patricio.contreras.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.models.repository.Asignatura;

public interface IAsignaturaDao extends JpaRepository<Asignatura, Long>{
	
	//Aqui  falta agregar informacion de alumnos y profesores

}
