package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.Entities.Produtos;
import model.dao.ProdutosDao;

public class ProdutosDaoJDBC implements ProdutosDao {

	private Connection conn;
	
	public ProdutosDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Produtos findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM produtos WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Produtos obj = new Produtos();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				obj.setVarDireto(rs.getDouble("CustoVarDireto"));
				obj.setVarIndireto(rs.getDouble("CustoVarDireto"));
				
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Produtos> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM produtos ORDER BY Nome");
			rs = st.executeQuery();

			List<Produtos> list = new ArrayList<>();

			while (rs.next()) {
				Produtos obj = new Produtos();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				obj.setVarDireto(rs.getDouble("CustoVarDireto"));
				obj.setVarIndireto(rs.getDouble("CustoVarDireto"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Produtos obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO produtos " +
				"(Nome, CustoVarDireto, CustoVarIndireto) " +
				"VALUES " +
				"(?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getCustoVarDireto());
			st.setDouble(3, obj.getCustoVarIndireto());
			

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Produtos obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE produtos " +
							"SET Nome = ?, CustoVarDireto = ?, CustoVarIndireto = ?" +
							"WHERE Id = ?");

			st.setString(1, obj.getNome());
	
			st.setDouble(2, obj.getCustoVarDireto());
			
			st.setDouble(3, obj.getCustoVarIndireto());
			
			st.setInt(4, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Produtos WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}