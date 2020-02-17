package com.patricio.contreras.models.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
@Entity
@Table(name = "asignaturas")
public class Asignatura implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique=true)
	private String nombre;
	//referencia alumno_asignatura
	//referencia profesor
	
	
	@ManyToMany(mappedBy = "asignaturas")
	private List<Alumno> alumnos;
	
	@OneToOne(mappedBy = "asignaturas")
	private Profesor profesor;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
