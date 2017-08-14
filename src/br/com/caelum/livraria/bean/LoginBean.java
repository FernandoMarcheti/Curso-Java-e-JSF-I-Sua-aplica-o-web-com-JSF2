package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ViewScoped
@ManagedBean
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public RedirectView efetuarLogin(){
		
		boolean existe = new UsuarioDAO(Usuario.class).existe(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(existe){
			context.getExternalContext().getSessionMap()
				.put("usuarioLogado", this.usuario);
			return new RedirectView("livro");
		}
		return null;
	}
}
