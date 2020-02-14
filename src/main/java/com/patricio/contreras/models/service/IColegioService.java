package com.patricio.contreras.models.service;

import java.util.List;

import com.patricio.contreras.models.repository.Colegio;

public interface IColegioService {
	
	public List<Colegio> findAll();
	
	public Colegio findById (Long id);
	
	public Colegio save(Colegio colegio);
	
	public void delete(Long id);
	

}
