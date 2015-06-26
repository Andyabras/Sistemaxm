package br.com.sistemaxm.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;

import br.com.sistemaxm.business.CadastroUsuario;
import br.com.sistemaxm.business.SessaoUtil;
import br.com.sistemaxm.entidades.Usuario;
import br.com.sistemaxm.repository.JpaUtil;
import br.com.sistemaxm.repository.UsuarioRepository;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	public LoginBean() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String autenticar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(true);

		try {
			trx.begin();
			CadastroUsuario cadastro = new CadastroUsuario(
					new UsuarioRepository(manager));
			usuario = cadastro.autenticar(usuario);
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Usuário logado com sucesso.",
					null));

			if (usuario.getCodigo() != 0) {
				SessaoUtil.setObjeto(httpSession, "login", this.usuario);

				return "/Master.xhtml?faces-redirect=true";
			} else {
				return "/TelaLogin.xhtml?faces-redirect=true";
			}

		} catch (Exception e) {
			trx.rollback();			
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Usuário Inexistente.",
					null));
			return "/TelaLogin.xhtml?faces-redirect=true";
		} finally {
			manager.close();
		}

	}

	public String invalidarSessao() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession httpSession = (HttpSession) context
					.getExternalContext().getSession(true);

			SessaoUtil.invalidar(httpSession, "login");

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}

		return "/TelaLogin.xhtml";
	}

}