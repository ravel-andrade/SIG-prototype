package model.services;


import java.util.List;

import model.Entities.Infraestrutura;
import model.dao.DaoFactory;
import model.dao.InfraestruturaDao;


public class InfraestruturaService {

	private InfraestruturaDao dao = DaoFactory.createInfraestruturaDao();

	public List<Infraestrutura> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Infraestrutura obj) {
		if (obj.getId() == null ) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Infraestrutura obj) {
		dao.deleteById(obj.getId());
	}
}