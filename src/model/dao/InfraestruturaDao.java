package model.dao;

import java.util.List;

import model.Entities.Infraestrutura;

public interface InfraestruturaDao {

	void insert(Infraestrutura obj);
	void update(Infraestrutura obj);
	void deleteById(Integer id);
	Infraestrutura findById(Integer id);
	List<Infraestrutura> findAll();
}