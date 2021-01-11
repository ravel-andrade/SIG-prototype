package model.services;


import java.util.List;

import model.Entities.Produtos;
import model.dao.DaoFactory;
import model.dao.ProdutosDao;


public class ProdutosService {

	private ProdutosDao dao = DaoFactory.createProdutosDao();

	public List<Produtos> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Produtos obj) {
		if (obj.getId() == null ) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Produtos obj) {
		dao.deleteById(obj.getId());
	}
}