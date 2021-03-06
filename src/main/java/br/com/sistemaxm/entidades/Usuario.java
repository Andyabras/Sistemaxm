package br.com.sistemaxm.entidades;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.security.*;
import java.math.*;

import javax.persistence.*;

import org.jboss.logging.MDC;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name="usuario", uniqueConstraints={@UniqueConstraint(columnNames=("login"))})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String login;
	private String nome;	
	private String senha;
	private String confirmaSenha;
	

	public Usuario() {
		
	}   
	@Id    
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}   
	@Column(nullable=false, unique=true)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		try {
			this.senha = Md5Criptografy.md5(senha);
		} catch (Exception e) {
			e.printStackTrace();

			this.senha = "";
		}
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		try {
			this.confirmaSenha = Md5Criptografy.md5(confirmaSenha);
		} catch (Exception e) {
			e.printStackTrace();

			this.confirmaSenha = "";
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo != other.codigo)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
	
	
		
	
   
}
