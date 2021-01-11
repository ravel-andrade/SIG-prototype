package model.dao;
import db.DB;
import model.dao.impl.FuncionariosDaoJDBC;
import model.dao.impl.InfraestruturaDaoJDBC;
import model.dao.impl.ProdutosDaoJDBC;



	public class DaoFactory {

		public static FuncionariosDao createFuncionariosDao() {
			return new FuncionariosDaoJDBC(DB.getConnection());
		}
		
		public static ProdutosDao createProdutosDao() {
			return new ProdutosDaoJDBC(DB.getConnection());
		}
		
		public static InfraestruturaDao createInfraestruturaDao() {
			return new InfraestruturaDaoJDBC(DB.getConnection());
		}
		
	}


