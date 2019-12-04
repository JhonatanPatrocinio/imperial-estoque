package br.ufac.si.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="categoriaControlador")
@SessionScoped
public class CategoriaControlador {

	private CategoriaGerente cg;
	private Categoria categoria;
	private String chave = "";
	
	public CategoriaControlador() {
		cg = new CategoriaGerente();
	}
	
	public String incluir() {
		this.categoria = new Categoria();
		return "categoriaInclusao";
	}
	
	public String adicionar() {
		cg.adicionar(categoria);
		return "categoriaGerenciador";
	}
	
	public String editar(Categoria categoria) {
		this.categoria = categoria;
		return "categoriaEdicao";
	}
	
	public String atualizar() {
		cg.atualizar(categoria);
		return "categoriaGerenciador";
	}
	
	public String excluir(Categoria categoria) {
		this.categoria = categoria;
		return "categoriaExclusao";
	}
	
	public String remover() {
		cg.remover(categoria);
		return "categoriaGerenciador";
	}
	
	public Categoria recuperar(int codigo) {
		return cg.recuperar(codigo);
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategoriaPorNomeContendo(){
		return cg.recuperarTodosPorNomeContendo(chave);
	}
	
	public List<Categoria> getCategoriaPorNome(){
		return cg.recuperarTodosPorNome();
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
}
