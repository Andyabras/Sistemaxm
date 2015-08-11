package br.com.sistemaxm.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sistemaxm.entidades.Carro;
import br.com.sistemaxm.entidades.Marca;

public class CarroRepository implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public CarroRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public CarroRepository() {
		
	}
	
	public Carro porId(Long id) {
		return manager.find(Carro.class, id);
	}
	
	public List<Carro> veiculosCombo() {
		TypedQuery<Carro> query = manager.createQuery("select new Carro(c.id, c.modelo, c.placa) from Carro c", Carro.class);
		return query.getResultList();
	}
	
	public List<Carro> todos() {
		TypedQuery<Carro> query = manager.createQuery("from Carro", Carro.class);
		return query.getResultList();
	}
	
	public void adicionar(Carro carro) {
		this.manager.persist(carro);
	}

	public void excluir(Carro carro) {
		this.manager.remove(carro);
	}
	
	public void editar(Carro carro) {
		this.manager.merge(carro);
	}
}
