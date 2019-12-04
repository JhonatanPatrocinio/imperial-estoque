package br.ufac.si.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.entidades.MovimentacaoEstoque;
import br.ufac.si.academico.entidades.MovimentacaoEstoqueItem;
import br.ufac.si.academico.entidades.Produto;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="movimentacaoControlador")
@SessionScoped
public class MovimentacaoControlador {

	private MovimentacaoEstoqueGerente mg;
	private MovimentacaoEstoque mve;
	private MovimentacaoEstoqueItem mvi;
	private String chave = "";
	
	public MovimentacaoControlador() {
		this.mg = new MovimentacaoEstoqueGerente();
	}
	
	public String incluir() {
		this.mve = new MovimentacaoEstoque();
		this.mvi = new MovimentacaoEstoqueItem();
		return "movimentoInclusao";
	}
	
	public String adicionar() {
		try {
			mg.adicionar(this.mve);
			
		} catch (Exception e) {
			
		}
		return "movimentoGerenciador";
	}
	
	public String adicionarItem() {
		try {
			this.mve.addItem(this.mvi.getProduto().getId(), this.mvi.getQuantidade());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public MovimentacaoEstoque getMve() {
		return mve;
	}

	public void setMve(MovimentacaoEstoque mve) {
		this.mve = mve;
	}
	
	public MovimentacaoEstoqueItem getMvi() {
		return mvi;
	}

	public void setMvi(MovimentacaoEstoqueItem mvi) {
		this.mvi = mvi;
	}

	public String visualizar(MovimentacaoEstoque mve) {
		this.mve = mve;
		return "visualizarMovimentacao";
	}

	public List<MovimentacaoEstoque> getMovimentacoesTodos(){
		return mg.recuperarTodos();
	}
//
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}


	
	
	
}
