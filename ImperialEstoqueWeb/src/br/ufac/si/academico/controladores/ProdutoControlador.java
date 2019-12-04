package br.ufac.si.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.academico.entidades.Produto;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="produtoControlador")
@SessionScoped
public class ProdutoControlador {

	private ProdutoGerente pg;
	private Produto produto;
	private String chave = "";
	
	public ProdutoControlador() {
		pg = new ProdutoGerente();
	}
	
	public String incluir() {
		this.produto = new Produto();
		return "produtoInclusao";
	}
	
	public String adicionar() {
		pg.adicionar(produto);
		return "produtoGerenciador";
	}
	
	public String editar(Produto produto) {
		this.produto = produto;
		return "produtoEdicao";
	}
	
	public String atualizar() {
		pg.atualizar(produto);
		return "produtoGerenciador";
	}
	
	public String excluir(Produto produto) {
		this.produto = produto;
		return "produtoExclusao";
	}
	
	public String remover() {
		pg.remover(produto);
		return "produtoGerenciador";
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getCategoriaPorNomeContendo(){
		return pg.recuperarTodosPorNomeContendo(chave);
	}
	
	public List<Produto> getProdutoPorNome(){
		return pg.recuperarTodosPorNome();
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Produto recuperar(Integer codigo) {
		return pg.recuperar(codigo);
	}
	
}
