package br.com.sistemaxm.business;

import java.io.Serializable;

import br.com.sistemaxm.entidades.Cliente;
import br.com.sistemaxm.repository.ClienteRepository;

public class CadastroClientes implements Serializable {
	
	ClienteRepository clienteRepo;
	
	public CadastroClientes(ClienteRepository clienteRepo) {
		this.clienteRepo = clienteRepo;
	}
	
	public CadastroClientes() {
		
	}
	
	public void salvar(Cliente cliente) {
		this.clienteRepo.salvar(cliente);
	}
	
	public void editar(Cliente cliente) {
		this.clienteRepo.editar(cliente);
	}
	
	public void excluir(Cliente cliente) {
		this.clienteRepo.excluir(cliente);
	}
}

