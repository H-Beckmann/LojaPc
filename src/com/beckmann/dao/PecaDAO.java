package com.beckmann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beckmann.model.CategoriaPeca;
import com.beckmann.model.Peca;

public class PecaDAO extends DAO<Peca> {
	
	public boolean create (Peca peca) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO peca ");
		sql.append("	(nome, description, preco, categoria, estoque) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?, ?, ?) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, peca.getNome());
			stat.setString(2, peca.getDesc());
			stat.setFloat(3, peca.getPreco());
			stat.setInt(4, peca.getCategoriaPeca().getId());
			stat.setInt(5, peca.getEstoque());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
			
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

	public boolean update(Peca peca) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE peca ");
		sql.append("	SET nome=?, description=?, preco=?, categoria=?, estoque=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, peca.getNome());
			stat.setString(2, peca.getDesc());
			stat.setFloat(3, peca.getPreco());
			stat.setInt(4, peca.getCategoriaPeca().getId());
			stat.setInt(5, peca.getEstoque());
			stat.setInt(6, peca.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Alteração realizada com sucesso.");
			
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
		sql.append("DELETE FROM peca ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remoção realizada com sucesso.");
			
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

	public List<Peca> findAll() {
		List<Peca> listaLivro = new ArrayList<Peca>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, description, preco, categoria, estoque ");
		sql.append("FROM ");
		sql.append("	peca ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Peca peca = null;
			
			while(rs.next()) {
				peca = new Peca();
				peca.setId(rs.getInt("id"));
				peca.setNome(rs.getString("nome"));
				peca.setDesc(rs.getString("description"));
				peca.setPreco(rs.getFloat("preco"));
				peca.setCategoriaPeca(CategoriaPeca.valueOf(rs.getInt("categoria")));
				peca.setEstoque(rs.getInt("estoque"));
				listaLivro.add(peca);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaLivro;
	}

	public List<Peca> findByDescricao(String descricao) {
		List<Peca> listaLivro = new ArrayList<Peca>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, description, preco, categoria, estoque ");
		sql.append("FROM ");
		sql.append("	peca ");
		sql.append("WHERE ");
		sql.append("	description ilike ? ");
		sql.append("ORDER BY description ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + descricao  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Peca peca = null;
			
			while(rs.next()) {
				peca = new Peca();
				peca.setId(rs.getInt("id"));
				peca.setNome(rs.getString("nome"));
				peca.setDesc(rs.getString("description"));
				peca.setPreco(rs.getFloat("preco"));
				peca.setCategoriaPeca(CategoriaPeca.valueOf(rs.getInt("categoria")));
				peca.setEstoque(rs.getInt("estoque"));
				listaLivro.add(peca);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaLivro;
	}	
	
	public List<Peca> findByNome(String nome) {
		List<Peca> listaLivro = new ArrayList<Peca>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, description, preco, categoria, estoque ");
		sql.append("FROM ");
		sql.append("	peca ");
		sql.append("WHERE ");
		sql.append("	nome ilike ? ");
		sql.append("ORDER BY nome ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + nome + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Peca peca = null;
			
			while(rs.next()) {
				peca = new Peca();
				peca.setId(rs.getInt("id"));
				peca.setNome(rs.getString("nome"));
				peca.setDesc(rs.getString("description"));
				peca.setPreco(rs.getFloat("preco"));
				peca.setCategoriaPeca(CategoriaPeca.valueOf(rs.getInt("categoria")));
				peca.setEstoque(rs.getInt("estoque"));
				listaLivro.add(peca);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaLivro;
	}
	
	public Peca findById(int id) {
		Peca peca = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, description, preco, categoria, estoque ");
		sql.append("FROM ");
		sql.append("	peca ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				peca = new Peca();
				peca.setId(rs.getInt("id"));
				peca.setDesc(rs.getString("description"));
				peca.setPreco(rs.getFloat("preco"));
				peca.setCategoriaPeca(CategoriaPeca.valueOf(rs.getInt("categoria")));
				peca.setEstoque(rs.getInt("estoque"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return peca;
	}

}