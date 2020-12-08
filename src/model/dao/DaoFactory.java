package model.dao;
import db.DB;


import model.dao.impl.FuncionariosDaoJDBC;


	public class DaoFactory {

		

		public static FuncionariosDao createFuncionariosDao() {
			return new FuncionariosDaoJDBC(DB.getConnection());
		}
	}


