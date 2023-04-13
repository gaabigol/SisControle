package com.SisControle.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "nota_entrada")
public class NotaEntrada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(nullable = false, name = "data_hora", columnDefinition = "DATETIME")
	@NotNull(message = "Por favor, informe a hora e a data")
	private LocalDateTime dataHora;

	@Transient
	private Float total;

	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;

	@OneToMany(mappedBy = "notaEntrada", cascade = CascadeType.ALL)
	private List<NotaEntradaItem> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Float getTotal() {
		this.total = 0f;
		if(this.itens != null) {
			for (NotaEntradaItem notaEntradaItem : itens) {
				total += notaEntradaItem.getValorTotal();
			}
		}
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<NotaEntradaItem> getItens() {
		return itens;
	}

	public void setItens(List<NotaEntradaItem> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotaEntrada [id=");
		builder.append(id);
		builder.append(", dataHora=");
		builder.append(dataHora);
		builder.append(", total=");
		builder.append(total);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataHora, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaEntrada other = (NotaEntrada) obj;
		return Objects.equals(dataHora, other.dataHora) && Objects.equals(id, other.id);
	}
}
