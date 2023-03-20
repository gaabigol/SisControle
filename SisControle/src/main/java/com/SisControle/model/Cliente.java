package com.SisControle.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.SisControle.model.enums.Sexo;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 30)
	private String nome;
	@Column(length = 11)
	private String cpf;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", columnDefinition = "DATE")
	private LocalDate dataNascimento;
	@Column(length = 15)
	private String telefone;
	@Column(length = 15)
	private String celular;
	@Column(length = 50)
	private String email;
	private boolean ativo;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public Cliente() {
		this.ativo = true;
	}

	public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento, String telefone, String celular,
			String email, boolean ativo, Sexo sexo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.ativo = ativo;
		this.sexo = sexo;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
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

	public Sexo getSexo() {
		return sexo;
	}

	@Override
	public String toString() {
		String cliente = "";
		cliente += "CLIENTE\n";
		cliente += "-------------------------\n";
		cliente += "ID.......: " + this.id + "\n";
		cliente += "Nome.....: " + this.nome + "\n";
		cliente += "CPF......: " + this.cpf + "\n";
		cliente += "Data Nasc: " + this.dataNascimento + "\n";
		cliente += "Sexo.....: " + (this.sexo == null ? "null" : this.sexo.getDescricao()) + "\n";
		cliente += "Telefone.: " + this.telefone + "\n";
		cliente += "Celular..: " + this.celular + "\n";
		cliente += "Email....: " + this.email + "\n";
		cliente += "Ativo....: " + (this.ativo ? "Sim" : "Não") + "\n";
		return cliente;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

}
