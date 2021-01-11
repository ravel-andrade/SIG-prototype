package model.Entities;

import java.io.Serializable;

public class CustoVarIndireto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double dia1;
	private Double dia2;
	private Double dia3;
	private Double dia4;
	
	
	public CustoVarIndireto() {
		super();
	}
	
	public CustoVarIndireto(Integer id, String nome, Double dia1, Double dia2, Double dia3, Double dia4) {
		super();
		this.id = id;
		this.nome = nome;
		this.dia1 = dia1;
		this.dia2 = dia2;
		this.dia3 = dia3;
		this.dia4 = dia4;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getDia1() {
		return dia1;
	}
	public void setDia1(Double dia1) {
		this.dia1 = dia1;
	}
	public Double getDia2() {
		return dia2;
	}
	public void setDia2(Double dia2) {
		this.dia2 = dia2;
	}
	public Double getDia3() {
		return dia3;
	}
	public void setDia3(Double dia3) {
		this.dia3 = dia3;
	}
	public Double getDia4() {
		return dia4;
	}
	public void setDia4(Double dia4) {
		this.dia4 = dia4;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia1 == null) ? 0 : dia1.hashCode());
		result = prime * result + ((dia2 == null) ? 0 : dia2.hashCode());
		result = prime * result + ((dia3 == null) ? 0 : dia3.hashCode());
		result = prime * result + ((dia4 == null) ? 0 : dia4.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		CustoVarIndireto other = (CustoVarIndireto) obj;
		if (dia1 == null) {
			if (other.dia1 != null)
				return false;
		} else if (!dia1.equals(other.dia1))
			return false;
		if (dia2 == null) {
			if (other.dia2 != null)
				return false;
		} else if (!dia2.equals(other.dia2))
			return false;
		if (dia3 == null) {
			if (other.dia3 != null)
				return false;
		} else if (!dia3.equals(other.dia3))
			return false;
		if (dia4 == null) {
			if (other.dia4 != null)
				return false;
		} else if (!dia4.equals(other.dia4))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustoVarIndireto [id=" + id + ", nome=" + nome + ", dia1=" + dia1 + ", dia2=" + dia2 + ", dia3=" + dia3
				+ ", dia4=" + dia4 + "]";
	}
	
	
	
	
	

}
