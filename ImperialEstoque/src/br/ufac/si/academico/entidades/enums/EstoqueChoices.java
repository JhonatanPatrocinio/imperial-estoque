package br.ufac.si.academico.entidades.enums;


public enum EstoqueChoices {
	ENTRADA("ENTRADA"),
	SAIDA("SAIDA");
	
	private String descricao;
	
	

	private EstoqueChoices(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
