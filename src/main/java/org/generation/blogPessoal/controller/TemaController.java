package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;

@RestController /*--->Indica que a classe irá responder pelos métodos http.*/
@RequestMapping("/tema") 
@CrossOrigin(origins = "*", allowedHeaders = "*") /*--->Esta anotação, além de liberar as origens, libera também os cabeçalhos das requisições*/
public class TemaController {

	@Autowired
	private TemaRepository repository;

	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Tema> post(@RequestBody Tema tema) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}

	
	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema) {
		
		System.out.println("Tema recebido: " + tema);
		return repository.findById(tema.getId()).map(resposta -> ResponseEntity.ok().body(repository.save(tema)))
		.orElse(ResponseEntity.notFound().build());  
		
	}
	
	@DeleteMapping("/{id}")
	  public ResponseEntity<?> deletePostagem(@PathVariable long id) {
			
			return repository.findById(id)
					.map(resposta -> { repository.deleteById(id);
						
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
	
	
	
	
}