package com.patricio.contreras.models.repository;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.NonNull;

//@Data
@NoArgsConstructor
//@AllArgsConstructor
@NonNull
@Entity
@Table(name = "alumnos")
public class Alumno extends Persona implements Serializable {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 * 
	 * @Column(nullable = false) private String nombre;
	 */

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno", fetch = FetchType.LAZY)
	private List<Nota> notas;

	@ManyToMany
	@JoinTable(name = "asignaturas_alumnos", joinColumns = @JoinColumn(name = "id_alumno"), inverseJoinColumns = @JoinColumn(name = "id_asignatura"))
	private List<Asignatura> asignaturas;

	public Alumno(List<Nota> notas, List<Asignatura> asignaturas, String nombre, LocalDate fechaNacimiento) {
		super(nombre, fechaNacimiento);
		this.notas = notas;
		this.asignaturas = asignaturas;
	}

	/*
	 * @Column(name = "fecha_nacimiento") private LocalDate fechaNacimiento;
	 * 
	 * 
	 * public void prePersit() { fechaNacimiento =LocalDate.now(); }
	 */

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	// referencias a alumnoAsignatura
	// referencia profesor
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
