package br.org.generation.lojaGame.controller;

import java.util.List;

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

import br.org.generation.lojaGame.model.Usuario;
import br.org.generation.lojaGame.repository.UsuarioRepository;

	@RestController
	@RequestMapping("/usuario")
	@CrossOrigin (origins = "*", allowedHeaders = "*" )  
	public class UsuarioController {
		
		
		@Autowired
		private UsuarioRepository usuarioRepository;


		@GetMapping("/{id}")
		public ResponseEntity <Usuario> getById(@PathVariable long id){
			return usuarioRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse( ResponseEntity.notFound().build());
}

		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<Usuario>> getByTitulo(@PathVariable String nome){
			return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
}

		@PostMapping
		public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
}

		@PutMapping
		public ResponseEntity<Usuario> putPostagem (@RequestBody Usuario usuario){
			return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
}

		@DeleteMapping("/{id}")
		public void deleteCategoria(@PathVariable long id) {
			usuarioRepository.deleteById(id);
}		
			
			
}
