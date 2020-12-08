package model.services;


import java.util.List;

import model.Entities.Funcionarios;
import model.dao.DaoFactory;
import model.dao.FuncionariosDao;


public class FuncionariosService {

	private FuncionariosDao dao = DaoFactory.createFuncionariosDao();

	public List<Funcionarios> findAll() {
		return dao.findAll();
	}
}