package br.ufac.si.academico.testes;

import java.util.Date;
import java.util.List;

import br.ufac.si.academico.entidades.MovimentacaoEstoque;
import br.ufac.si.academico.entidades.MovimentacaoEstoqueItem;
import br.ufac.si.academico.entidades.enums.EstoqueChoices;
import br.ufac.si.academico.gerentes.MovimentacaoEstoqueGerente;

public class MovimentacaoEstoqueTeste {

	public MovimentacaoEstoqueTeste() {

		MovimentacaoEstoqueGerente gerenteEstoque = new MovimentacaoEstoqueGerente();
		MovimentacaoEstoque mv1, mv2, mv3, mv4, recmv;

		try {
			System.out.println("Criando Movimento de ENTRADA");
			mv1 = new MovimentacaoEstoque(EstoqueChoices.ENTRADA, new Date());
			mv1.addItem(1, 5);
			mv1.addItem(2, 5);
			System.out.println(mv1);
			System.out.println("Persistindo ENTRADA no Banco de Dados");
			gerenteEstoque.adicionar(mv1);
			recmv = gerenteEstoque.recuperar(1);
			System.out.println(recmv);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("Criando Movimento de ENTRADA 2");
			mv2 = new MovimentacaoEstoque(EstoqueChoices.ENTRADA, new Date());
			mv2.addItem(3, 5);
			mv2.addItem(4, 5);
			mv2.addItem(5, 5);
			mv2.addItem(6, 5);
			System.out.println(mv2);
			System.out.println("Persistindo ENTRADA 2 no Banco de Dados");
			gerenteEstoque.adicionar(mv2);
			recmv = gerenteEstoque.recuperar(2);
			System.out.println(recmv);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			System.out.println("Criando Movimento de SAIDA");
			mv3 = new MovimentacaoEstoque(EstoqueChoices.SAIDA, new Date());
			mv3.addItem(5, 5);
			mv3.addItem(6, 2);
			System.out.println(mv3);
			System.out.println("Persistindo SAIDA no Banco de Dados");
			gerenteEstoque.adicionar(mv3);
			recmv = gerenteEstoque.recuperar(3);
			System.out.println(recmv);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			System.out.println("Criando Movimento de SAIDA 2 ");
			mv4 = new MovimentacaoEstoque(EstoqueChoices.SAIDA, new Date());
			mv4.addItem(1, 5);
			mv4.addItem(2, 7);
			System.out.println(mv4);
			System.out.println("Persistindo SAIDA 2 no Banco de Dados");
			gerenteEstoque.adicionar(mv4);
			recmv = gerenteEstoque.recuperar(4);
			System.out.println(recmv);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		List<MovimentacaoEstoque> movimentos = gerenteEstoque.recuperarTodos();
		for (MovimentacaoEstoque mv : movimentos) {
			System.out.println("Movimentação "+ mv.getId());
			System.out.println("Tipo: " + mv.getTipo());
			System.out.println("Itens:");
			for (MovimentacaoEstoqueItem mvi : mv.getItens()) {
				System.out.println("Item: " + mvi.getId() + ", Nome: " + mvi.getProduto().getNome() + ", Quantidade em Estoque: " + mvi.getProduto().getQuantidade());
			}
			
		}

		gerenteEstoque.encerrar();

	}

}
