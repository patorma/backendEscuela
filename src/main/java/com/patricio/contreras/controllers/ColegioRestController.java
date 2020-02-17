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

import com.patricio.contreras.models.repository.Colegio;
import com.patricio.contreras.models.service.IColegioService;

@RestController
@RequestMapping("/api")
public class ColegioRestController {
	
	@Autowired
	private IColegioService colegioService;
	
	//Se muestra todos los colegios 
	@GetMapping("/colegios")
	public List<Colegio> index(){
		return colegioService.findAll();
		
	}
	
	@GetMapping("/colegios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		//inicializamos el objeto colegio en null
		Colegio colegio = null;
		
		//vamos a ocupar map para responder con mensajes
		Map<String, Object> response = new HashMap<>();
		
		try {
			colegio = colegioService.findById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(colegio == null) {
			response.put("mensaje", "El colegio ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Colegio>(colegio,HttpStatus.OK);
		
	}
	
	@PostMapping("/colegios")
	public ResponseEntity<?> create(@RequestBody Colegio colegio){
		
		//inicializamos el nuevo colegio creado
		
		Colegio colegioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			colegioNew = colegioService.save(colegio);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El colegio ha sido creado con éxito! ");
		response.put("colegio",colegioNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/colegios/{id}")
	public ResponseEntity<?> update(@RequestBody Colegio colegio,@PathVariable Long id){
		
		//Buscamos por id el colegio a modificar
		Colegio colegioActual = colegioService.findById(id);
		
		//Inicializamos el colegio con las actualizaciones realizadas
		Colegio colegioUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		// en caso de no encontrar el colegio asociado a ese id
		if(colegioActual == null) {
			response.put("mensaje", "Error: no se pudo editar el colegio ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			//modificamos los datos del colegio actual con los datos del colegio que te envien
			colegioActual.setNombre(colegio.getNombre());
			colegioActual.setDireccion(colegio.getDireccion());
			
			colegioUpdated = colegioService.save(colegioActual);
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el colegio en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El colegio ha sido actualizado con éxito!");
		response.put("colegio", colegioUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED) ;
		
	}
	
	@DeleteMapping("/colegios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		//Map para guardar el contenido que enviaremos en el ResponseEntity con mensajes
		Map<String, Object> response = new HashMap<>();
		
		try {
			//Automaticamente se valida que el id del cliente existe en la BD
			colegioService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el colegio de la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El colegio fue eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

}
