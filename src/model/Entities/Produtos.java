package model.Entities;

import java.io.Serializable;

public class Produtos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	private Double custoVarDireto;
	private Double custoVarIndireto;
	
	
	public Produtos() {
		super();
	
	}
	
	public Produtos(Integer id, String nome, Double CustoVarDireto, Double CustoVarIndireto) {
		super();
		this.id = id;
		this.nome = nome;
		this.custoVarDireto =CustoVarDireto;
		this.custoVarIndireto =CustoVarIndireto;
	
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
	
	public void setVarIndireto(double custoVarIndireto) {
		this.custoVarIndireto = custoVarIndireto;
	}
	
	public Double getCustoVarDireto() {
		return custoVarDireto;
	}
	
	public void setVarDireto(double custoVarDireto) {
		this.custoVarDireto = custoVarDireto;
	}
	
	public Double getCustoVarIndireto() {
		return custoVarIndireto;
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
		Produtos other = (Produtos) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Funcionarios [id=" + id + ", nome=" + nome + ", custoVarDireto=" + custoVarDireto + ", custoVarIndireto=" + custoVarIndireto + "]";
	}

	

}
