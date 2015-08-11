package br.com.sistemaxm.business;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessaoUtil {
	
	public static synchronized Object getObjeto(HttpSession httpSession, String attrib) throws Exception
	 {
		Object obj = httpSession.getAttribute(attrib);
		
		if (obj == null) {
			return null;
		}
		return obj;
		
	}
	
	public static synchronized void setObjeto(HttpSession httpSession, String attrib, Object obj) throws Exception {
		httpSession.setAttribute(attrib, obj);
	}
	
	public static synchronized void invalidar(HttpSession httpSession, String attrib)  {
		try {
			httpSession.setAttribute(attrib, null);
			httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			httpSession.invalidate();
		}
		catch (Exception e) {
			throw e;
		}
	}

}
