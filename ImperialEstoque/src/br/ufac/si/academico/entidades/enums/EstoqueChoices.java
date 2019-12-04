package br.ufac.si.academico.entidades.enums;

public enum EstoqueChoices {
	ENTRADA(0, "Entrada"),
	SAIDA(1, "Saida");
	
	private Integer cod;
	private String descricao;
	
	private EstoqueChoices(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstoqueChoices toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (EstoqueChoices x : EstoqueChoices.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
	
}
