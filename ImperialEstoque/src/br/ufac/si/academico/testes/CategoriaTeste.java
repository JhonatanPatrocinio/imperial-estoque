package br.ufac.si.academico.testes;


import java.util.ArrayList;
import java.util.List;

import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.gerentes.CategoriaGerente;

public class CategoriaTeste {

	public CategoriaTeste() {
		
		CategoriaGerente gerenteCategoria = new CategoriaGerente();
		Categoria cat1, cat2, cat3, cat4, cat5;
		System.out.println("Categoria TESTE");
		// Nome, Descrição
		cat1 = new Categoria("Eletronicos", "Produtos destinados a eletronicos da imperial");
		cat2 = new Categoria("Roupas", "Produtos destinados a blusas, shorts e moletons da imperial");
		cat3 = new Categoria("Bones", "Produtos destinados a bonés da imperial");
		cat4 = new Categoria("Copos", "Produtos destinados aos copos da imperial");
		cat5 = new Categoria("Tirantes", "Produtos destinados aos tirantes eletronicos da imperial");
		
		System.out.println("Criado as 5 Categorias");
		System.out.println(cat1);
		System.out.println(cat2);
		System.out.println(cat3);
		System.out.println(cat4);
		System.out.println(cat5);
		
		
		System.out.println("Persistindo as 5 categorias no Banco de Dados");
		
		gerenteCategoria.adicionar(cat1);
		gerenteCategoria.adicionar(cat2);
		gerenteCategoria.adicionar(cat3);
		gerenteCategoria.adicionar(cat4);
		gerenteCategoria.adicionar(cat5);
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias = gerenteCategoria.recuperarTodos();
		for (Categoria cat : categorias) {
			System.out.println("Categoria " + cat.getId() + ": " + cat);
		}
		System.out.println("Recuperando Categoria 1");
		Categoria catRecuperada = gerenteCategoria.recuperar(1);
		System.out.println("Alterando a descrição");
		System.out.println("Antes: " + catRecuperada.getDescricao());
		catRecuperada.setDescricao("Os Produtos eletronicos feito para a Imperial");
		gerenteCategoria.atualizar(catRecuperada);
		catRecuperada = gerenteCategoria.recuperar(1);
		System.out.println("Depois: " + catRecuperada.getDescricao());

	}
}
