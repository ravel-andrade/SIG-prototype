package model.Entities;

import java.util.Date;
import java.util.List;

public class Funcionarios{
	private int id;
	private String nome;
	private String funcao;
	private Double salario;
	private int cargaHoraria;

	public Funcionarios(int id, String nome, String funcao, Double salario, int cargaHoraria) {
		this.id = id;
		this.nome = nome;
		this.funcao = funcao;
		this.salario = salario;
		this.cargaHoraria = cargaHoraria;
	}

	public Funcionarios() {
		
	}
	

}
