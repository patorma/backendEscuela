package com.patricio.contreras.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.models.dao.IColegioDao;
import com.patricio.contreras.models.repository.Colegio;

@Service
public class ColegioServiceImpl implements IColegioService {
	
	//se hace inyeccion de dependencias con @AutoWired
		//se inyecta el cliente DAO
	
	@Autowired
	IColegioDao colegioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Colegio> findAll() {
	
		return colegioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Colegio findById(Long id) {
		
		return colegioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Colegio save(Colegio colegio) {
		
		return colegioDao.save(colegio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		colegioDao.deleteById(id);

	}

}
