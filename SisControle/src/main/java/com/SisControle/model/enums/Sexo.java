package com.SisControle.model.enums;

public enum Sexo {

	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String destricao;

	Sexo(String descricao) {
		this.destricao = descricao;
	}

	public String getDescricao() {
		return destricao;
	}
}
