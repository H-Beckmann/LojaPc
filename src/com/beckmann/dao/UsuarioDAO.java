package com.beckmann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beckmann.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	public boolean create (Usuario usuario) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("	(nome, login, senha, data_nasc) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ?) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getLogin());
			stat.setString(3, usuario.getSenha());
			stat.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclus�o realizada com sucesso.");
			
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

	public boolean update(Usuario usuario) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario ");
		sql.append("	SET nome=?, login=?, senha=?, data_nasco=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getLogin());
			stat.setString(3, usuario.getSenha());
			stat.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stat.setInt(5, usuario.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Altera��o realizada com sucesso.");
			
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

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remo��o realizada com sucesso.");
			
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

	public List<Usuario> findAll() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, data_nasc");
		sql.append("FROM ");
		sql.append("	usuario ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				
				listaUsuario.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaUsuario;
	}
	
	public Usuario findById(int id) {
		Usuario usuario = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, data_nasc");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return usuario;
	}
	
	public Usuario verificarLoginSenha(String login, String senha) {
		Usuario usuario = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, data_nasc");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	login = ? ");
		sql.append("	AND senha = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return usuario;
	}
}