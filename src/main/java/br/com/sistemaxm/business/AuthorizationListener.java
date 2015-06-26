package br.com.sistemaxm.business;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.sistemaxm.entidades.Usuario;

public class AuthorizationListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		// Obtém o contexto atual
		FacesContext contexto = event.getFacesContext();

		// Obtém a página que atualmente está interagindo com o ciclo
		String paginaAtual = contexto.getViewRoot().getViewId();

		// Se for a página 'login.jsp' seta a variável como true
		boolean isLoginPage = (paginaAtual.lastIndexOf("/TelaLogin.xhtml") > -1);
		
		if(!isLoginPage){
			isLoginPage = (paginaAtual.lastIndexOf("/CadastroUsuario.xhtml") > -1);
		}

		// Obtém a sessão atual
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(false);

		// Restaga o nome do usuário logado
		Usuario login = (Usuario) sessao.getAttribute("login");

		// Verifica se o usuário está logado e se não está na página de login
		if (!isLoginPage && login == null) {
			// Redireciona o fluxo para a página de login
			NavigationHandler nh = contexto.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(contexto, null, "/TelaLogin.xhtml");
		}

		if (isLoginPage && login != null) {
			// Se o usuário logado tentar acessar a página de login ele é
			// redirecionado para a página inicial
			NavigationHandler nh = contexto.getApplication()
					.getNavigationHandler();
			
			if (paginaAtual.equals("/TelaLogin.xhtml")) {
			nh.handleNavigation(contexto, null,  "/Master.xhtml");
			} else {
				nh.handleNavigation(contexto, null,  "/CadastroUsuario.xhtml");
			}
		}
	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}