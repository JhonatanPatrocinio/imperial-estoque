package br.ufac.si.academico.testes;

import java.util.ArrayList;
import java.util.List;

import br.ufac.si.academico.entidades.Categoria;
import br.ufac.si.academico.entidades.Produto;
import br.ufac.si.academico.gerentes.CategoriaGerente;
import br.ufac.si.academico.gerentes.ProdutoGerente;

public class ProdutoTeste {
	
	public ProdutoTeste() {
//	public static void main(String[] args) {

		ProdutoGerente produtoGerente = new ProdutoGerente();
		CategoriaGerente gerenteCategoria = new CategoriaGerente();
		Produto p1, p2, p3,p4, p5, p6;
		
		Categoria catEletronicos, catRoupas, catBones, catCopos, catTirantes;
		
		System.out.println("Produto Teste");
		System.out.println("Recuperando as Categorias");
		
		catEletronicos = gerenteCategoria.recuperar(1);
		catRoupas = gerenteCategoria.recuperar(2);
		catBones = gerenteCategoria.recuperar(3);
		catCopos = gerenteCategoria.recuperar(4);
		catTirantes = gerenteCategoria.recuperar(5);
		
		gerenteCategoria.encerrar();
		
		System.out.println("Criando os Produtos");
		
		p1 = new Produto("MP3", 15.11, "Ouvidor de Aúdio");
		p2 = new Produto("Tirante", 16.11, "Acompanha Tirante 10cm");
		p3 = new Produto("Copo Vermelho JAVA", 10.11, "Caneca Du Bom");
		p4 = new Produto("Blusa Imperal 1", 40.11, "Blusa de Reliquia");
		p5 = new Produto("Chapéu Imperial Vermelho", 15.11, "Único Chapeu que temos");
		p6 = new Produto("Copo Star Wars", 60.11, "Copo Preto TOP");
		
		System.out.println("Setando as Categorias Recuperadas");
		p1.setCategoria(catEletronicos);
		p2.setCategoria(catTirantes);
		p3.setCategoria(catCopos);
		p4.setCategoria(catRoupas);
		p5.setCategoria(catBones);
		p6.setCategoria(catCopos);

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);
		System.out.println(p6);
		
		System.out.println("Persistindo os Produtos Criados no Banco de Dados");
		
		produtoGerente.adicionar(p1);
		produtoGerente.adicionar(p2);
		produtoGerente.adicionar(p3);
		produtoGerente.adicionar(p4);
		produtoGerente.adicionar(p5);
		produtoGerente.adicionar(p6);
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = produtoGerente.recuperarTodos();
		
		for (Produto p : produtos) {
			System.out.println("Produto " + p.getId() + ": "+ p);
		}
		
		System.out.println("Recuperando o Produto de ID 2");
		Produto produtoRecuperado = produtoGerente.recuperar(2);
		System.out.println("Alterando o Preço");
		System.out.println("Antes: " + produtoRecuperado.getPreco());
		produtoRecuperado.setPreco(20.00);
		produtoGerente.atualizar(produtoRecuperado);
		produtoRecuperado = produtoGerente.recuperar(2);
		System.out.println("Depois: " + produtoRecuperado.getPreco());
		
		
		produtoGerente.encerrar();
		
//		//RECUPERANDO NOME DE PRODUTO
//		System.out.println(produtoGerente.recuperar(3).getNome());
//		//RECUPERANDO NOME DE PRODUTO
//		
//		//MUDANDO NOME DO PRODUTO
//		produtoGerente.recuperar(3).setNome("CANECAAAA");
//		produtoGerente.atualizar(produtoGerente.recuperar(3));
//		//MUDANDO NOME DO PRODUTO
//		
//		//DELETANDO PRODUTO
//		produtoGerente.remover(produtoGerente.recuperar(4));
//		//DELETANDO PRODUTO
//		
	}

}
