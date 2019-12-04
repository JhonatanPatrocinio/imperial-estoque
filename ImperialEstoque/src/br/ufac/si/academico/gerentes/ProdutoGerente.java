package br.ufac.si.academico.gerentes;

import java.util.List;

import javax.persistence.*;

import br.ufac.si.academico.entidades.Produto;

public class ProdutoGerente {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ProdutoGerente() {
		emf = Persistence.createEntityManagerFactory("ImperialEstoque");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Produto produto) {
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
	}
	
	public Produto recuperar(int id) {
		return em.find(Produto.class, id);
	}
	
	public void atualizar(Produto produto) {
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
	}
	
	public void remover(Produto produto) {
		em.getTransaction().begin();
		em.remove(produto);
		em.getTransaction().commit();
	}
	
	public List<Produto> recuperarTodos(){
		return em.createNamedQuery("Produto.todos").getResultList();
	}
	
	public List<Produto> recuperarTodosPorNome(){
		return em.createNamedQuery("Produto.todosPorNome").getResultList();
	}
	
	public List<Produto> recuperarTodosPorNomeContendo(String termo){
		return em.createNamedQuery("Produto.todosPorNomeContendo").setParameter("termo", "%" + termo + "%").getResultList();
	}
	
	
	public void encerrar() {
		em.close();
		emf.close();
	}

}
