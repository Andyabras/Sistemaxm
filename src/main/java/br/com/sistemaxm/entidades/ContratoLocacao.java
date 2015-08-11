package br.com.sistemaxm.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="contrato_locacao")
public class ContratoLocacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;	
	private Cliente cliente;
	private Carro carro;
	private Date dataInicioLocacao;
	private Date dataFimLocacao;
	private Long numeroLocacao;
	
	public ContratoLocacao() {
		super();
	}
	
	public Date getDataInicioLocacao() {
		return dataInicioLocacao;
	}

	public void setDataInicioLocacao(Date dataInicioLocacao) {
		this.dataInicioLocacao = dataInicioLocacao;
	}

	public Date getDataFimLocacao() {
		return dataFimLocacao;
	}

	public void setDataFimLocacao(Date dataFimLocacao) {
		this.dataFimLocacao = dataFimLocacao;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
	@JoinColumn(name="cod_cliente")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToOne
	@JoinColumn(name="cod_carro")
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Long getNumeroLocacao() {
		return numeroLocacao;
	}

	public void setNumeroLocacao(Long numeroLocacao) {
		this.numeroLocacao = numeroLocacao;
	}
	
	

}
