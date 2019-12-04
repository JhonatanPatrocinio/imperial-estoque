package br.ufac.si.academico.entidades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "produtos")
@NamedQueries({
	@NamedQuery(name="Produto.todos", query="SELECT p FROM Produto p"),
	@NamedQuery(name="Produto.todosPorNome", query="SELECT p FROM Produto p ORDER BY p.nome"),
	@NamedQuery(name="Produto.todosPorNomeContendo", query="SELECT p FROM Produto p WHERE p.nome LIKE :termo ORDER BY p.nome")
	})
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "PRECO",nullable = false)
	private Double preco;
	
	@Column(name = "DESCRICAO",nullable = true, length = 180)
	private String descricao;
	
	@Column(name = "QUANTIDADE",nullable = false)
	private Integer quantidade = 0;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORIA_FK")
	private Categoria categoria;
	
	public Produto() {

	}

	public Produto(String nome, Double preco, String descricao) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao
				+ ", quantidade=" + quantidade + ", categoria=" + categoria + "]";
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
