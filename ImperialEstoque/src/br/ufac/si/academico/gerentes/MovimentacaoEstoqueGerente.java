package br.ufac.si.academico.gerentes;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import br.ufac.si.academico.entidades.MovimentacaoEstoque;
import br.ufac.si.academico.entidades.MovimentacaoEstoqueItem;
import br.ufac.si.academico.entidades.Produto;
import br.ufac.si.academico.entidades.enums.EstoqueChoices;
import br.ufac.si.academico.exceptions.ProdutoIndisponivelException;

public class MovimentacaoEstoqueGerente {
	private EntityManagerFactory emf;
	private EntityManager em;

	public MovimentacaoEstoqueGerente() {
		emf = Persistence.createEntityManagerFactory("ImperialEstoque");
		em = emf.createEntityManager();

	}

	public void adicionar(MovimentacaoEstoque movimentoEstoque) throws ProdutoIndisponivelException {
		em.getTransaction().begin();
		movimentoEstoque.setData(new Date());
		System.out.println(movimentoEstoque.getTipo());
		em.persist(movimentoEstoque);

		if (movimentoEstoque.getTipo() == EstoqueChoices.ENTRADA) {
			for(MovimentacaoEstoqueItem item : movimentoEstoque.getItens()) {
				Produto produto = item.getProduto();
				Integer quantidadeAtual = produto.getQuantidade();
				item.getProduto().setQuantidade(quantidadeAtual + item.getQuantidade());
				em.merge(produto);
			}
		} else if (movimentoEstoque.getTipo() == EstoqueChoices.SAIDA){
			for(MovimentacaoEstoqueItem item : movimentoEstoque.getItens()) {
				Produto produto = item.getProduto();
				if(produto.getQuantidade() < item.getQuantidade()) {
					em.getTransaction().rollback();
					throw new ProdutoIndisponivelException("Não temos a quantidade " + item.getQuantidade() + " do Produto " + produto.getNome() + ". A quantidade atual é: " + produto.getQuantidade());
				}
				Integer quantidadeAtual = produto.getQuantidade();
				item.getProduto().setQuantidade(quantidadeAtual - item.getQuantidade());
				em.merge(produto);
			}
		}
		em.getTransaction().commit();
	}
	
	public MovimentacaoEstoque recuperar(int id) {
		return em.find(MovimentacaoEstoque.class, id);
	}
	
	public void atualizar(MovimentacaoEstoque movimentoEstoque) {
		
		em.getTransaction().begin();
		em.merge(movimentoEstoque);
		em.getTransaction().commit();
		
	}
	
	public void remover(MovimentacaoEstoque movimentoEstoque) {
		
		em.getTransaction().begin();
		em.remove(movimentoEstoque);
		em.getTransaction().commit();
		
	}
	
	public List<MovimentacaoEstoque> recuperarTodos(){
		return em.createNamedQuery("MovimentacaoEstoque.todos").getResultList();
	}
	
	public List<MovimentacaoEstoque> recuperarTodosPorData(){
		return em.createNamedQuery("MovimentacaoEstoque.todosPorData").getResultList();
	}
	
	public List<MovimentacaoEstoque> recuperarTodosPorTipo(Integer tipo){
		return em.createNamedQuery("MovimentacaoEstoque.todosPorTipo").setParameter("termo", tipo).getResultList();
	}
	
    public List<MovimentacaoEstoque> recuperarPorTipo(EstoqueChoices choice) {
        final Query query = em.createQuery("SELECT mv FROM MovimentacaoEstoque mv WHERE mv.tipo = :tipo ORDER BY mv.data");
        query.setParameter("tipo", choice);
        return query.getResultList();
    }
	
	public void encerrar() {
		em.close();
		emf.close();
	}
}
