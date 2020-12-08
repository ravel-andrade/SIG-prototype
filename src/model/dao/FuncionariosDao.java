package model.dao;

import java.util.List;

import model.Entities.Funcionarios;

public interface FuncionariosDao {

	void insert(Funcionarios obj);
	void update(Funcionarios obj);
	void deleteById(Integer id);
	Funcionarios findById(Integer id);
	List<Funcionarios> findAll();
}