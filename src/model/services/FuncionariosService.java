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
	
	public void saveOrUpdate(Funcionarios obj) {
		if (obj.getId() == null ) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Funcionarios obj) {
		dao.deleteById(obj.getId());
	}
}