package com.SisControle.model.enums;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("feminino");

	private String destricao;

	Sexo(String descricao) {
		this.destricao = descricao;
	}

	public String getDescricao() {
		return destricao;
	}
}
