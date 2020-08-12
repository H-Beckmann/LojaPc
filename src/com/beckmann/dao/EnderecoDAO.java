package com.beckmann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.beckmann.model.Endereco;

public class EnderecoDAO extends DAO<Endereco>{

	@Override
	public boolean create(Endereco endereco) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.endereco ");
		sql.append("	(rua, numero, cidade, quadra, estado) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?, ?, ?) ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setString(1, endereco.getRua());
			stat.setInt(2, endereco.getNumero());
			stat.setString(3, endereco.getCidade());
			stat.setString(4, endereco.getQuadra());
			stat.setString(5, endereco.getEstado());
			stat.execute();
			
			conn.commit();
			
			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;	
	}

	@Override
	public boolean update(Endereco entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Endereco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
