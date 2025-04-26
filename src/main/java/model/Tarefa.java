package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tarefa {
	private int id;
	private String titulo;
	private String descricao;
	private StatusTarefa status;
	private LocalDateTime dataCriacao;
	private LocalDate dataLimite;
	private Prioridade prioridade;

	public Tarefa() {
	}

	public Tarefa(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCriacao = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public LocalDate getDataLimite() {
		return dataLimite;
	}
	
	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	public Prioridade getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public String toString() {
	    return "Tarefa{" +
	            "id=" + id +
	            ", titulo='" + titulo + '\'' +
	            ", descricao='" + descricao + '\'' +
	            ", status=" + status +
	            ", dataCriacao=" + dataCriacao +
	            ", dataLimite=" + dataLimite +
	            ", prioridade=" + prioridade +
	            '}';
	}


}
