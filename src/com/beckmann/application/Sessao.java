package com.beckmann.application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Sessao {

	private static Sessao sessao = null;

	private Sessao() {
	}

	public static Sessao getInstance() {
		if (sessao == null)
			sessao = new Sessao();
		return sessao;
	}

	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext é exclusivo para uma requisição HTTP.");
		}
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public Object getAttribute(String key) {
		return getExternalContext().getSessionMap().get(key);
	}

	public void setAttribute(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);
	}

	public void invalidateSession() {
		getExternalContext().invalidateSession();
	}

}
