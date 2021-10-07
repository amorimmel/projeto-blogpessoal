package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.model.UsuarioLogin;
import br.org.generation.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin (origins = "*", allowedHeaders = "*" )  
public class UsuarioController {
	
	// injeção de dependência da classe de Serviço UsuarioService p ter acesso aos métodos do CRUD
	
	@Autowired
	private UsuarioService usuarioService;

	
	//Executa listarUsuarios da classe de serviço para visualizar todos os usuários. 
	 
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
	
		return ResponseEntity.ok(usuarioService.listarUsuarios());

	}
	
	
	
	/**
	 * Executa autenticarUsuario da classe de serviço para efetuar o login na api. 
	 * Caso o login ok, os dados do usuário/token são exibidos.
	 */
	
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.autenticarUsuario(user)
			.map(respostaAutenticacao -> ResponseEntity.ok(respostaAutenticacao))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	
	}
		
		/**
		 * Executa cadastrarUsuario da classe de serviço para criar um novo usuário na api. 
		 * Caso cadastro tenha sido ok, os dados do usuário são exibidos.
		 */
		
	
		
		@PostMapping("/cadastrar")
		public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {

			return usuarioService.cadastrarUsuario(usuario)
				.map(respostaCadastro -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastro))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
		
		
}
