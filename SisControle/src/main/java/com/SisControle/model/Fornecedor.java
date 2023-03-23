package com.SisControle.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "Por favor, informe a razão social")
	@Size(min = 3, max = 50, message = "Por favor, digite um nome válido")
	private String razaoSocial;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "Por favor, informe seu nome")
	@Size(min = 3, max = 50, message = "Digite um nome válido")
	private String nomeFantasia;

	@Column(length = 19)
	@CNPJ(message = "CNPJ inválido")
	private String cnpj;

	@Column(length = 15)
	private String telefone;

	@Column(length = 15)
	private String celular;

	@Column(length = 50)
	@Email(message = "E-mail inválido")
	private String email;

	private boolean ativo;

	public Fornecedor() {
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fornecedor [id=");
		builder.append(id);
		builder.append(", razaoSocial=");
		builder.append(razaoSocial);
		builder.append(", nomeFantasia=");
		builder.append(nomeFantasia);
		builder.append(", cnpj=");
		builder.append(cnpj);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", celular=");
		builder.append(celular);
		builder.append(", email=");
		builder.append(email);
		builder.append(", ativo=");
		builder.append(ativo);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(id, other.id);
	}

}
