package br.ufac.si.academico.entidades;

import java.util.*;
import javax.persistence.*;

import br.ufac.si.academico.entidades.enums.EstoqueChoices;
import br.ufac.si.academico.exceptions.ProdutoIndisponivelException;
import br.ufac.si.academico.exceptions.ProdutoRepetidoException;
import br.ufac.si.academico.gerentes.ProdutoGerente;


@Entity
@Table(name = "movimentacoes_estoque")
@NamedQueries({
	@NamedQuery(name="MovimentacaoEstoque.todos", query="SELECT mv FROM MovimentacaoEstoque mv"),
	@NamedQuery(name="MovimentacaoEstoque.todosPorData", query="SELECT mv FROM MovimentacaoEstoque mv ORDER BY mv.data"),
	})
public class MovimentacaoEstoque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private EstoqueChoices tipo;
	
	@Column(name = "DATA", nullable = false)
	private Date data;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "ITENS_ID")
	private List<MovimentacaoEstoqueItem> itens = new ArrayList<MovimentacaoEstoqueItem>();
	
	
	public MovimentacaoEstoque() {
	}
	
	public MovimentacaoEstoque(EstoqueChoices tipo, Date data) {
		super();
		this.tipo = tipo;
		this.data = data;
	}


	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}	

	public EstoqueChoices getTipo() {
		return tipo;
	}

	public void setTipo(EstoqueChoices tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public void addItem(Integer idProduto, Integer quantidadeProduto) throws ProdutoIndisponivelException, ProdutoRepetidoException {
		ProdutoGerente gerenteProduto = new ProdutoGerente();
		Produto produto = gerenteProduto.recuperar(idProduto);
		gerenteProduto.encerrar();
		if(this.tipo == EstoqueChoices.SAIDA && produto.getQuantidade() < quantidadeProduto) {
			throw new ProdutoIndisponivelException("Não temos a quantidade " + quantidadeProduto + " do Produto " + produto.getNome() + ". A quantidade atual é: " + produto.getQuantidade());
		}
		MovimentacaoEstoqueItem item = new MovimentacaoEstoqueItem(quantidadeProduto, this, produto);
		for (MovimentacaoEstoqueItem i : this.itens) {
			if (i.getProduto().getId() == item.getProduto().getId()) {
				throw new ProdutoRepetidoException("Este produto já contem na Movimentação");
			}
		}
		this.itens.add(item);
	}
	
	public void removeItem(MovimentacaoEstoqueItem item){
		this.itens.remove(item);
	}
	
	public List<MovimentacaoEstoqueItem> getItens(){
		return this.itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoEstoque other = (MovimentacaoEstoque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimentacaoEstoque [id=" + id + ", tipo=" + tipo + ", data=" + data.toString() + ", itens=" + itens.toString() + "]";
	}


	
	
	
}
