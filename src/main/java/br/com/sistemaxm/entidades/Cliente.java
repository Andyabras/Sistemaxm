package br.com.sistemaxm.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.transaction.Transactional;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private int codigo;
	private Pessoa cliente;
	
	@Transient
	private boolean pf;
	
	@Transient
	private boolean pj;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodigo() {
		return codigo;
	}
	
	public boolean isPf() {
		return pf;
	}
	public void setPf(boolean pf) {
		this.pf = pf;
	}
	public boolean isPj() {
		return pj;
	}
	public void setPj(boolean pj) {
		this.pj = pj;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	
	

}
