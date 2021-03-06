package br.com.sistemaxm.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.com.sistemaxm.business.CadastroCarros;
import br.com.sistemaxm.entidades.Carro;
import br.com.sistemaxm.repository.CarroRepository;
import br.com.sistemaxm.repository.JpaUtil;

@ManagedBean(name="dtEditViewCar")
@ViewScoped
public class EditViewCar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Carro> carros;
	CarroRepository carroRepo;
	private Carro carro = new Carro();
	

	public void init() {
		this.carros = carroRepo.todos();
		
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public void onRowEdit(RowEditEvent event) {		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		this.carro = (Carro) event.getObject();
		
		try {
			trx.begin();
			CadastroCarros cadastro = new CadastroCarros(new CarroRepository(manager));
			this.carro.setId(((Carro)manager.find(Carro.class, carro.getId())).getId());
			carro.setMarca(carro.getMarca());
			carro.setModelo(carro.getModelo());			
			carro.setPlaca(carro.getPlaca());	
			carro.setRenavam(carro.getRenavam());
			carro.setCambio(carro.getCambio());
			carro.setChassi(carro.getChassi());
			carro.setCombustivel(carro.getCombustivel());
			carro.setCor(carro.getCor());
			carro.setKm(carro.getKm());
			carro.setQtdePortas(carro.getQtdePortas());
			carro.setUf(carro.getUf());
			carro.setAnoFabricacao(carro.getAnoFabricacao());
			carro.setAnoModelo(carro.getAnoModelo());
			carro.setAirBagMotorista(carro.isAirBagMotorista());
			carro.setAirBagPassageiro(carro.isAirBagPassageiro());
			carro.setAr(carro.isAr());		
			carro.setAlarme(carro.isAlarme());
			carro.setBancosCouro(carro.isBancosCouro());
			carro.setBloqueador(carro.isBloqueador());
			carro.setCdMp3Player(carro.isCdMp3Player());
			carro.setDirecao(carro.isDirecao());
			carro.setFreioAbs(carro.isFreioAbs());
			carro.setGps(carro.isGps());
			carro.setRastreador(carro.isRastreador());
			carro.setRodaLigaLeve(carro.isRodaLigaLeve());
			carro.setSensorRe(carro.isSensorRe());
			carro.setTrava(carro.isAirBagMotorista());
			carro.setVidro(carro.isVidro());			
			cadastro.editar(this.carro);
			this.carro = new Carro();
			trx.commit();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro editado com sucesso!",null));
		}
		catch (Exception e) {
			trx.rollback();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro não editado!",null));
		}
		finally {
			manager.close();
		}
	}
	
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro alterado", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
	}
	
	public String decodeBoolean(boolean value) {
		if (value=true) {
			return "Sim";
		}
		else {
			return "Não";
		}
	}
}
