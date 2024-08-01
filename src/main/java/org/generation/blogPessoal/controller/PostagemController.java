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

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;




@RestController                                   

                                                      /*--->Indica que a classe irá responder 
                                                       * pelos métodos http.*/
@RequestMapping("/postagens")                         /*---> End point das requisições feitas pela URL.*/
@CrossOrigin(origins = "*", allowedHeaders = "*")      /*--->Esta anotação, além de liberar as origens, 
                                                         libera também os cabeçalhos das requisições*/
public class PostagemController {
	  
	@Autowired                                     /*Injeção de independência, forma um objeto de classe 
	                                               controladora como inversão de controle */
	private PostagemRepository postagemrepository;
  
  @GetMapping
  public ResponseEntity<List<Postagem>> Getall(){
	  
	  return ResponseEntity.ok(postagemrepository.findAll()); //retornará cód 200 OK.
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Postagem> GetById(@PathVariable long id){
	
	  return postagemrepository.findById(id)
			  .map(resp -> ResponseEntity.ok(resp))
			  .orElse(ResponseEntity.notFound().build());
	  
  }
  
  @GetMapping("/titulo/{titulo}")
   public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		
	  return ResponseEntity.ok(postagemrepository.findAllByTituloContainingIgnoreCase(titulo));
	}
  
  @PostMapping
  public ResponseEntity<Postagem> post (@Valid @RequestBody Postagem postagem){
	  
	  return ResponseEntity.status(HttpStatus.CREATED).body(postagemrepository.save(postagem));
  }
  
  @PutMapping
  public ResponseEntity<Postagem> put (@Valid @RequestBody Postagem postagem){
		
		return postagemrepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(postagemrepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
  
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return postagemrepository.findById(id)
				.map(resposta -> {
					postagemrepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
  }
}

