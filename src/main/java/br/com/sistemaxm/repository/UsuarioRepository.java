package br.com.sistemaxm.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.sistemaxm.entidades.Usuario;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public UsuarioRepository() {
		
	}
	
	public Usuario porId(Long codigo) {
		return manager.find(Usuario.class, codigo);
	}
	
	
	
	public List<Usuario> todos() {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}
	
	public void adicionar(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public void excluir(Usuario usuario) {
		this.manager.remove(usuario);
	}
	
	public Usuario autenticar(Usuario entity) {
		try{
		String sql = "from Usuario u WHERE u.login = :login"
				+ " and u.senha = :senha";
		//sql = sql.replace("#", this.getClass().getName());
		
		TypedQuery<Usuario> q = this.manager.createQuery(sql, Usuario.class);
		q.setParameter("login", entity.getLogin());
		q.setParameter("senha", entity.getSenha());
		
		entity = q.getSingleResult();
		
		return entity;
		
		} catch (Exception e) {
			throw e;
		}
	}
	
}
