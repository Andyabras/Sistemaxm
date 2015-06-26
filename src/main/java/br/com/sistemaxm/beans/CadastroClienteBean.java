package br.com.sistemaxm.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.sistemaxm.entidades.Cliente;
import br.com.sistemaxm.entidades.PessoaFisica;
import br.com.sistemaxm.entidades.PessoaJuridica;
import br.com.sistemaxm.enums.Sexo;

@ManagedBean
@ViewScoped
public class CadastroClienteBean {
	
	private Cliente cliente = new Cliente();	
	private PessoaFisica pessoaFisica = new PessoaFisica();
	private PessoaJuridica pessoaJuridica = new PessoaJuridica();
	
	public List<SelectItem> getTipoItemListSexo() {
		List<SelectItem> auxLista = new ArrayList<SelectItem>();
		for (Sexo sexo : Sexo.values()) {
			auxLista.add(new SelectItem(sexo, sexo.getValue()));
		}
		return auxLista;
	}
	
	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}



	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public void vcePjPf(ValueChangeEvent e) {
		System.out.println(e.getNewValue());
		
		String s = (String) e.getNewValue();
		
		if (s.equals("pj")) {
			this.cliente.setPf(false);
			this.cliente.setPj(true);
		} else {
			this.cliente.setPf(true);
			this.cliente.setPj(false);
		}
	}
	
	}
