package br.ufac.si.academico.gerentes;

import java.util.List;

import javax.persistence.*;

import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.entidades.Produto;

public class CategoriaGerente {
	private EntityManagerFactory emf;
	private EntityManager em;

	public CategoriaGerente() {
		emf = Persistence.createEntityManagerFactory("ImperialEstoque");
		em = emf.createEntityManager();

	}


	public void adicionar(Categoria categoria) {

		em.getTransaction().begin();
		em.persist(categoria);
		em.getTransaction().commit();
		
	}
	
	public Categoria recuperar(Integer id) {
		return em.find(Categoria.class, id);
	}
	
	public void atualizar(Categoria categoria) {
		
		em.getTransaction().begin();
		em.merge(categoria);
		em.getTransaction().commit();
		
	}
	
	public void remover(Categoria categoria) {
		
		em.getTransaction().begin();
		for (Produto prod: categoria.getProdutos()) {
			prod.setCategoria(null);
			em.merge(prod);
		}
		em.remove(categoria);
		em.getTransaction().commit();
		
	}
	
	public List<Categoria> recuperarTodos(){
		return em.createNamedQuery("Categoria.todos").getResultList();
	}
	
	public List<Categoria> recuperarTodosPorNome(){
		return em.createNamedQuery("Categoria.todosPorNome").getResultList();
	}
	
	public List<Categoria> recuperarTodosPorNomeContendo(String termo){
		return em.createNamedQuery("Categoria.todosPorNomeContendo").setParameter("termo", "%" + termo + "%").getResultList();
	}
	
	public void encerrar() {
		em.close();
		emf.close();
	}
}
