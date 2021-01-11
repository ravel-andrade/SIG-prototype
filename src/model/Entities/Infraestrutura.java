package model.Entities;

import java.io.Serializable;

public class Infraestrutura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Integer quantidade;
	private Double valorUN;
	
	public Infraestrutura() {
		super();
	}

	public Infraestrutura(Integer id, String descricao, Integer quantidade, Double valorUN) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valorUN = valorUN;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUN() {
		return valorUN;
	}

	public void setValorUN(Double valorUN) {
		this.valorUN = valorUN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + id;
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valorUN == null) ? 0 : valorUN.hashCode());
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
		Infraestrutura other = (Infraestrutura) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valorUN == null) {
			if (other.valorUN != null)
				return false;
		} else if (!valorUN.equals(other.valorUN))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Infraestrutura [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", valorUN="
				+ valorUN + "]";
	}
	
	
	
	
	

	

}
