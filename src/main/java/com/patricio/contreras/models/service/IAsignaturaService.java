package com.patricio.contreras.models.service;

import java.util.List;

import com.patricio.contreras.models.repository.Asignatura;

public interface IAsignaturaService {

	public 	List<Asignatura> findAll();
	
	public Asignatura findById (Long id);
	
	public Asignatura save(Asignatura asignatura);
	
	public void delete(Long id);
	
}
