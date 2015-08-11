package br.com.sistemaxm.beans;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sistemaxm.business.SessaoUtil;
import br.com.sistemaxm.entidades.Usuario;

public abstract class AbstractMb<T> {

	protected FacesContext facesContext = null;

	protected HttpSession httpSession = null;

	protected static Logger logger; // importa as classes com crtl shift O

	protected Usuario usuario = null;

	public AbstractMb() {
		this.facesContext = FacesContext.getCurrentInstance();
		this.httpSession = (HttpSession) this.facesContext.getExternalContext()
				.getSession(true);

		try {
			this.usuario = (Usuario) SessaoUtil.getObjeto(
					this.httpSession, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {
		// logger = Logger.getLogger("beanLogger");
	}

	public abstract List<T> autocomplete(String suggest);

	public abstract String clear();

	public abstract String create();

	public abstract String delete();

	// public abstract String insert();

	// public abstract String newObject();

	public abstract String saveOrUpdate();

	public abstract String search();

	// public abstract String update();

	

	

}