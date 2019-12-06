package br.ufac.si.academico.controladores;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.ufac.si.academico.entidades.MovimentacaoEstoque;
import br.ufac.si.academico.entidades.MovimentacaoEstoqueItem;
import br.ufac.si.academico.entidades.enums.EstoqueChoices;
import br.ufac.si.academico.exceptions.ProdutoIndisponivelException;
import br.ufac.si.academico.exceptions.ProdutoRepetidoException;
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
			if (this.mve.getTipo() != null) {
				System.out.println(this.mve.getTipo());
				mg.adicionar(this.mve);				
			}else {
				System.out.println("NULL TIPO");
			}
			
		} catch (ProdutoIndisponivelException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("movimento:tipo", new FacesMessage("" + e));
			return null;
		}
		return "movimentoGerenciador";
	}
	
	public String adicionarItem() {
		try {
			System.out.println(this.mvi.getProduto().getNome());
			this.mve.addItem(this.mvi.getProduto().getId(), this.mvi.getQuantidade());
		} catch (ProdutoIndisponivelException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("movimento_item:quantidade", new FacesMessage("" + e));
		} catch (ProdutoRepetidoException e) {
			System.out.println(e);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("movimento_item:produto", new FacesMessage("" + e));
		}
		return null;
	}
	
	public void removerItem(MovimentacaoEstoqueItem item) {
		System.out.println("REMOVEEEEE");
		this.mve.removeItem(item);
		System.out.println(this.mve.getItens());
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

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public EstoqueChoices[] getChoices() {
		return EstoqueChoices.values();
	}
	
	public EstoqueChoices recuperarChoice(String valueString) {
		return EstoqueChoices.valueOf(valueString);
	}
	
//	public SelectItem[] getChoices() {
//		SelectItem[] items = EstoqueChoices.values().size();
//		  int i = 0;
//		  for (Object x : EstoqueChoices.values()) {
//		    items[i++] = new SelectItem(x, x.toString());
//		  }
//		  return items;
//	}


	
	
	
}
