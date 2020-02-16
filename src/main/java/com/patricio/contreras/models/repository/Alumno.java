package com.patricio.contreras.models.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "alumnos")
public class Alumno extends Persona implements Serializable{
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;*/
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "alumno",fetch = FetchType.LAZY)
	private List<Nota> notas;
	
	@ManyToMany
	@JoinTable(
			name = "asignatura_alumnos",
			joinColumns = @JoinColumn(name= "id_alumno"),
			inverseJoinColumns =  @JoinColumn(name="id_asignatura"))
	private List<Asignatura> asignaturas;
	
	/*@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	
	public void prePersit() {
		fechaNacimiento =LocalDate.now();
	}*/
	
	//referencias a alumnoAsignatura
	//referencia profesor
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
