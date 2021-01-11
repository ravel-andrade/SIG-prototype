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
import model.Entities.Infraestrutura;
import model.dao.InfraestruturaDao;

public class InfraestruturaDaoJDBC implements InfraestruturaDao {

	private Connection conn;
	
	public InfraestruturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Infraestrutura findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM infraestrutura WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Infraestrutura obj = new Infraestrutura();
				obj.setId(rs.getInt("Id"));
				obj.setDescricao(rs.getString("Descricao"));
				obj.setQuantidade(rs.getInt("Quantidade"));
				obj.setValorUN(rs.getDouble("ValorUN"));
				
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
	public List<Infraestrutura> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM infraestrutura ORDER BY Descricao");
			rs = st.executeQuery();

			List<Infraestrutura> list = new ArrayList<>();

			while (rs.next()) {
				Infraestrutura obj = new Infraestrutura();
				obj.setId(rs.getInt("Id"));
				obj.setDescricao(rs.getString("Descricao"));
				obj.setQuantidade(rs.getInt("Quantidade"));
				obj.setValorUN(rs.getDouble("ValorUN"));
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
	public void insert(Infraestrutura obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO infraestrutura " +
				"(Descricao, Quantidade, ValorUN) " +
				"VALUES " +
				"(?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getDescricao());
			st.setInt(2, obj.getQuantidade());
			st.setDouble(3, obj.getValorUN());
			

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
	public void update(Infraestrutura obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE infraestrutura " +
							"SET Descricao = ?, Quantidade = ?, ValorUN = ?" +
							"WHERE Id = ?");

			st.setString(1, obj.getDescricao());
			st.setInt(2, obj.getQuantidade());
			st.setDouble(3, obj.getValorUN());
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
				"DELETE FROM Infraestrutura WHERE Id = ?");

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