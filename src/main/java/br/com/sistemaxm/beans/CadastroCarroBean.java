package br.com.sistemaxm.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.sistemaxm.business.CadastroCarros;
import br.com.sistemaxm.business.CadastroMarcas;
import br.com.sistemaxm.entidades.Carro;
import br.com.sistemaxm.entidades.Marca;
import br.com.sistemaxm.enums.AcessorioEnum;
import br.com.sistemaxm.enums.Cambio;
import br.com.sistemaxm.enums.Combustivel;
import br.com.sistemaxm.repository.CarroRepository;
import br.com.sistemaxm.repository.JpaUtil;
import br.com.sistemaxm.repository.MarcaRepository;

@ManagedBean
@ViewScoped
public class CadastroCarroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Carro carro = new Carro();	
	private List<Marca> marcas;
	private List<Carro> carros;
		
	@PostConstruct
	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			MarcaRepository marcaRepo = new MarcaRepository(manager);
			this.marcas = marcaRepo.todos();
		}
		finally {
			manager.close();
		}
	}
	
	
	public List<SelectItem> getTipoItemListCambio() { 
		List<SelectItem> auxLista = new ArrayList<SelectItem>();
		for (Cambio cambioEnum : Cambio.values()) {
			auxLista.add(new SelectItem(cambioEnum, cambioEnum.getValue()));
		}
		return auxLista;
	}
	
	
	public List<SelectItem> getTipoItemListCombustivel() {
		List<SelectItem> auxLista = new ArrayList<SelectItem>();
		for (Combustivel combustivelEnum : Combustivel.values()) {
			auxLista.add(new SelectItem(combustivelEnum, combustivelEnum.getValue()));
		}
		return auxLista;
	}


	public Cambio[] getCambio() {
		return Cambio.values();
	}
	
	public Combustivel[] getCombustivel() {
		return Combustivel.values();
	}
	
				
	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroCarros cadastro = new CadastroCarros(new CarroRepository(manager));
			cadastro.salvar(carro);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Veiculo cadastrado com sucesso!",null));
			this.carro = new Carro();
			trx.commit();
		}
		catch(Exception e) {
			trx.rollback();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao cadastrar veiculo!", null));
		}
		finally {
			manager.close();
		}
	}
	
	public void excluir() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroCarros cadastro = new CadastroCarros(new CarroRepository(manager));
			carro = manager.find(Carro.class, carro.getId());
			cadastro.excluir(carro);
			this.carro = new Carro();
			trx.commit();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro excluido com sucesso!",null));
		}
		catch (Exception e) {
			trx.rollback();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro não excluido!",null));
		}
		finally {
			manager.close();
		}
	}
	
	public List<Marca> getMarcas() {
		return marcas;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}	

	
}
