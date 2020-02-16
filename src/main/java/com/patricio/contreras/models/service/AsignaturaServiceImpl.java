package com.patricio.contreras.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.models.dao.IAsignaturaDao;
import com.patricio.contreras.models.repository.Asignatura;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {
	
	@Autowired
	IAsignaturaDao asignaturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAll() {
		// TODO Auto-generated method stub
		return asignaturaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Asignatura findById(Long id) {
		// TODO Auto-generated method stub
		return asignaturaDao.findById(id).orElse(null);
	}

	@Override
	public Asignatura save(Asignatura asignatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
