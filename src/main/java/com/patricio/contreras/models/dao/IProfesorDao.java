package com.patricio.contreras.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.models.repository.Profesor;

public interface IProfesorDao extends JpaRepository<Profesor, Long>{

}
