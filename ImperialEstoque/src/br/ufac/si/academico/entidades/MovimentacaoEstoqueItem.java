package br.ufac.si.academico.entidades;

import javax.persistence.*;


@Entity
@Table(name = "movimentacoes_estoque_itens")
public class MovimentacaoEstoqueItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "MOVIMENTACOES_ESTOQUE_ID")
	private MovimentacaoEstoque estoque;
	
	
	@ManyToOne
	@JoinColumn(name = "PRODUTOS_ID")
	private Produto produto;
	
	
	public MovimentacaoEstoqueItem() {
		
	}

	public MovimentacaoEstoqueItem(Integer quantidade, MovimentacaoEstoque estoque, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.estoque = estoque;
		this.produto = produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public MovimentacaoEstoque getEstoque() {
		return estoque;
	}

	public void setEstoque(MovimentacaoEstoque estoque) {
		this.estoque = estoque;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		MovimentacaoEstoqueItem other = (MovimentacaoEstoqueItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimentacaoEstoqueItem [id=" + id + ", quantidade=" + quantidade + ", produto=" + produto.toString() + "]";
	}


	
	

}
