package com.patricio.contreras.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.models.repository.Asignatura;
import com.patricio.contreras.models.service.IAsignaturaService;

@RestController
@RequestMapping("/api")
public class AsignaturaRestController {

	@Autowired
	private IAsignaturaService asignaturaService;

	@GetMapping("/asignaturas")
	public List<Asignatura> index() {
		return asignaturaService.findAll();
	}

	@GetMapping("/asignaturas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		// inicializamos el objeto asignatura en null
		Asignatura asignatura = null;

		// vamos a ocupar map para responder con mensajes
		Map<String, Object> response = new HashMap<>();

		try {
			asignatura = asignaturaService.findById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (asignatura == null) {
			response.put("mensaje","La asignatura con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Asignatura>(asignatura, HttpStatus.OK);
	}

	@PostMapping("/asignaturas")
	public ResponseEntity<?> create(@RequestBody Asignatura asignatura) {

		// se inicializa la nueva asignatura a crear
		Asignatura asignaturaNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			asignaturaNew = asignaturaService.save(asignatura);
		
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La asignatura ha sido creado con éxito! ");
		response.put("asignatura", asignaturaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/asignaturas/{id}")
	public ResponseEntity<?> update(@RequestBody Asignatura asignatura ,@PathVariable Long id){
		//Buscamos el id de la asignatura a modificar
		Asignatura asignaturaActual = asignaturaService.findById(id);
		
		//inicializamos la asignatura con los cambios realizados
		Asignatura asignaturaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		//en cason de no encontrar la asignatura asociada a ese id
		if(asignaturaActual == null) {
			response.put("mensaje", "Error: no se pudo editar la asignatura con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			asignaturaActual.setNombre(asignatura.getNombre());
			asignaturaUpdated = asignaturaService.save(asignaturaActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la asignatura en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La asignatura ha sido actualizado con éxito!");
		response.put("asignatura",asignaturaUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED) ;
	}
	@DeleteMapping("asignaturas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		//Map para guardar el contenido que enviaremos en el ResponseEntity con mensajes
		Map<String, Object> response = new HashMap<>();
		try {
			asignaturaService.delete(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la asignatura de la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La asignatura fue eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
}
