package model.Entities;

import java.io.Serializable;

public class Funcionarios implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String funcao;
	private Double salario;
	private Integer cargaHoraria;
	
	public Funcionarios() {
		
	}
	
	public Funcionarios(Integer id, String nome, String funcao, Double salario, int cargaHoraria) {
		super();
		this.id = id;
		this.nome = nome;
		this.funcao = funcao;
		this.salario = salario;
		this.cargaHoraria = cargaHoraria;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionarios other = (Funcionarios) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Funcionarios [id=" + id + ", nome=" + nome + ", funcao=" + funcao + ", salario=" + salario
				+ ", cargaHoraria=" + cargaHoraria + "]";
	}

	

}
