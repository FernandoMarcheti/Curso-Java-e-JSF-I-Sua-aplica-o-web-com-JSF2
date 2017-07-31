package br.com.caelum.livraria.util;

public class RedirectView {

	private String view;

	public RedirectView(String view) {
		super();
		this.view = view;
	}
	
	@Override
	public String toString() {
		return this.view + "?faces-redirect=true";
	}
}
