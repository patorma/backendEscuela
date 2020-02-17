package com.patricio.contreras.models.service;

import java.util.List;

import com.patricio.contreras.models.repository.Profesor;

public interface IProfesorService {
	
	public List<Profesor> findAll();
	
	public Profesor findById(Long id);
	
	public Profesor save(Profesor profesor);
	
	public void delete(Long id);

}
