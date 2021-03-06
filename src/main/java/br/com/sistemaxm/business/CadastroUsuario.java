package br.com.sistemaxm.business;

import java.util.List;

import br.com.sistemaxm.entidades.Usuario;
import br.com.sistemaxm.repository.UsuarioRepository;

public class CadastroUsuario {

	private UsuarioRepository usuRepo;
	
	public CadastroUsuario(UsuarioRepository usuRepo) {
		this.usuRepo = usuRepo;
	}
	
	public CadastroUsuario() {
		
	}
	
	public void salvar(Usuario usuario) {
		this.usuRepo.adicionar(usuario);
	}
	
	public void excluir(Usuario usuario) {
		this.usuRepo.excluir(usuario);
	}
	
	public Usuario autenticar(Usuario entity) {
		return this.usuRepo.autenticar(entity);
	}
	
	public List<Usuario> todosUsuarios() {
		return this.usuRepo.todos();
	}
	
}
