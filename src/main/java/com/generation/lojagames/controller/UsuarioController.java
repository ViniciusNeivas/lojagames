package com.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public ResponseEntity<List <Usuario>> getAll() {
		
		return ResponseEntity.ok (usuarioRepository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long Id){
		
		return usuarioRepository.findById(Id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse (ResponseEntity.notFound().build());
	} 
	
	
}
