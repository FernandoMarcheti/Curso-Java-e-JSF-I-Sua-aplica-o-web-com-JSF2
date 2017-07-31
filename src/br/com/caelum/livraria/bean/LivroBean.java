package br.com.caelum.livraria.bean;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public void gravarAutor(){
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		System.out.println(autor.getNome());
		livro.adicionaAutor(autor);
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					"autor", new FacesMessage("Livro deve conter pelo menos 1 Autor"));
			return;
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
		livro = new Livro();
	}
	
	public RedirectView formAutor(){
		return new RedirectView("autor");
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
		String valor = o.toString();
		if(!valor.startsWith("1")){
			throw new ValidatorException(new FacesMessage("Deve começar com 1"));
		}
	}

}
