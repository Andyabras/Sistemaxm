package br.com.sistemaxm.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import br.com.sistemaxm.entidades.Cliente;

public class ClienteRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManager manager;

	public ClienteRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public ClienteRepository() {
		
	}
	
	public Cliente porid(Long id) {
		return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> todos() {
		TypedQuery<Cliente> query = manager.createQuery("from Cliente",Cliente.class);
		return query.getResultList();
	}
	
	public List<Cliente> nomesCliente() {
		TypedQuery<Cliente> query = manager.createQuery("Select new Cliente(c.codigo, c.cliente) from Cliente c", Cliente.class);
		return query.getResultList();
	}
	
		
	public void salvar(Cliente cliente) {
		this.manager.persist(cliente);
	}
	
	public void editar(Cliente cliente) {
		this.manager.merge(cliente);
	}
	
	public void excluir(Cliente cliente) {
		this.manager.remove(cliente);
	}
}
