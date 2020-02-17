package com.patricio.contreras.models.repository;

import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Table;





import lombok.NoArgsConstructor;
import lombok.NonNull;


//@Data

@NoArgsConstructor
//@AllArgsConstructor
@NonNull
@Entity
@Table(name = "profesores")
public class Profesor extends Persona implements Serializable {
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;*/
	
	@Column(nullable = false)
	private boolean activo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colegio")
	private Colegio colegio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_asignatura",referencedColumnName = "id")
	private Asignatura asignaturas;
	

	public Profesor(boolean activo,Colegio colegio, Asignatura asignaturas,String nombre, LocalDate fechaNacimiento) {
		super(nombre, fechaNacimiento);
		this.activo = activo;
		this.colegio = colegio;
		this.asignaturas = asignaturas;
	}
	
	
	/*@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@PrePersist
	public void prePersist() {
		fechaNacimiento = LocalDate.now();
	}*/
	
	//falta lo de colegio y asignatura las referencias
	

	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public Colegio getColegio() {
		return colegio;
	}


	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}


	public Asignatura getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(Asignatura asignaturas) {
		this.asignaturas = asignaturas;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
