package br.org.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.blogpessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	//* Método que vai buscar um usuário pelo seu usuario-email 

	public Optional<Usuario> findByUsuario(String usuario);
	
	//testes
	
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
	//testes
	
	public Usuario findByNome(String nome);

}
