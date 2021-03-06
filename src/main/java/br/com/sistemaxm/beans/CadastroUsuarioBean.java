package br.com.sistemaxm.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.sistemaxm.business.CadastroUsuario;
import br.com.sistemaxm.entidades.Usuario;
import br.com.sistemaxm.repository.JpaUtil;
import br.com.sistemaxm.repository.UsuarioRepository;

@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	
	
		
	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuario.getSenha().equals(usuario.getConfirmaSenha()))	{			
		
		try {
			trx.begin();
			CadastroUsuario cadastro = new CadastroUsuario(new UsuarioRepository(manager));
			cadastro.salvar(usuario);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário cadastrado com sucesso!", null));
			this.usuario = new Usuario();
			trx.commit();
		}
		catch(Exception e) {
			trx.rollback();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário já cadastrado!",null));
		}
		finally {
			manager.close();
		}
		}
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha incorreta", null));
		}
			
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

