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
import model.Entities.Funcionarios;
import model.dao.FuncionariosDao;

public class FuncionariosDaoJDBC implements FuncionariosDao {

	private Connection conn;
	
	public FuncionariosDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Funcionarios findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM funcionarios WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Funcionarios obj = new Funcionarios();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				obj.setFuncao(rs.getString("Funcao"));
				obj.setSalario(rs.getDouble("Salario"));
				obj.setCargaHoraria(rs.getInt("CargaHoraria"));
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
	public List<Funcionarios> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM funcionarios ORDER BY Nome");
			rs = st.executeQuery();

			List<Funcionarios> list = new ArrayList<>();

			while (rs.next()) {
				Funcionarios obj = new Funcionarios();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Nome"));
				obj.setFuncao(rs.getString("Funcao"));
				obj.setSalario(rs.getDouble("Salario"));
				obj.setCargaHoraria(rs.getInt("CargaHoraria"));
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
	public void insert(Funcionarios obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO funcionarios " +
				"(Nome, Funcao, Salario, CargaHoraria) " +
				"VALUES " +
				"(?, ?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getFuncao());
			st.setDouble(3, obj.getSalario());
			st.setInt(4, obj.getCargaHoraria());

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
	public void update(Funcionarios obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE funcionarios " +
				"SET Name = ? " +
				"SET Funcao = ? " +
				"SET Salario = ? " +
				"SET CargaHoraria = ? " +
				"WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
			st.setString(3, obj.getFuncao());
			st.setInt(2, obj.getCargaHoraria());

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
				"DELETE FROM funcionarios WHERE Id = ?");

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