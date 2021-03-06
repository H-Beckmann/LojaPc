package com.beckmann.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beckmann.model.*;

public class VendaDAO extends DAO<Venda> {
	
	@Override
	public boolean create(Venda venda) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.venda");
		sql.append(" (data, idusuario) ");
		sql.append("VALUES ");
		sql.append(" (?, ?) " );
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(venda.getData()));
			stat.setInt(2, venda.getUsuario().getId());
			
			stat.execute();
			ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			venda.setId(rs.getInt("id"));
			for (ItemVenda itemVenda : venda.getListaItemVenda()) {
				itemVenda.setVenda(venda);
				if (createItemVenda(itemVenda, conn) == false) {
					throw new Exception("Erro ao incluir um item de venda");
				}				
				
			}
			
			conn.commit();

			System.out.println("Inclus�o realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;	
		
	}
	
	 
	private boolean createItemVenda(ItemVenda itemVenda, Connection conn) throws Exception{
		
		boolean retorno = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.itemvenda ");
		sql.append("	(valor, idvenda, idpeca) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?) ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setFloat(1, itemVenda.getValor());
			stat.setInt(2, itemVenda.getVenda().getId());
			stat.setInt(3, itemVenda.getPeca().getId());
			stat.execute();
		
			if (atualizarEstoque(itemVenda.getPeca(), conn) == false) 
				throw new Exception("Erro ao atualizar o estoque");
			
			
			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
		}
		return retorno;	
		
	}
	
	private boolean atualizarEstoque(Peca peca, Connection conn) {

		boolean retorno = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE peca SET estoque = estoque -1 ");
		sql.append("WHERE id = ? ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setInt(1, peca.getId());

			stat.execute();

			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
		}
		return retorno;	

	}	
	
	public List<Venda> findByUsuario(int idUsuario) {
		List<Venda> listaVenda = new ArrayList<Venda>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.data, ");
		sql.append("  u.id as idusuario, ");
		sql.append("  u.nome, ");
		sql.append("  u.login,  ");
		sql.append("  u.senha, ");
		sql.append("  u.data_nasc ");					
		sql.append("	FROM 	");
		sql.append("  public.venda v, ");
		sql.append("  public.usuario u ");
		sql.append("WHERE ");
		sql.append("  v.idusuario = u.id AND ");
		sql.append("  u.id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setInt(1, idUsuario);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData( rs.getDate("data").toLocalDate() );
				venda.setUsuario(new Usuario());
				venda.getUsuario().setId(rs.getInt("idusuario"));
				venda.getUsuario().setNome(rs.getString("nome"));
				venda.getUsuario().setLogin(rs.getString("login"));
				venda.getUsuario().setSenha(rs.getString("senha"));
				Date data = rs.getDate("data_nasc");
				venda.getUsuario().setDataNascimento(data == null? null : data.toLocalDate());
				ItemVendaDAO dao = new ItemVendaDAO();
				venda.setListaItemVenda(dao.findByVenda(venda));
				listaVenda.add(venda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaVenda;
	}

	@Override
	public List<Venda> findAll() {
		List<Venda> listaVenda = new ArrayList<Venda>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.data, ");
		sql.append("  u.id, ");
		sql.append("  u.nome, ");
		sql.append("  u.login,  ");
		sql.append("  u.senha, ");
		sql.append("  u.data_nasc ");					
		sql.append("	FROM 	");
		sql.append("  public.venda v, ");
		sql.append("  public.usuario u ");
		sql.append("WHERE ");
		sql.append("  v.idusuario = u.id ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
					
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData( rs.getDate("data").toLocalDate() );
				venda.setUsuario(new Usuario());
				venda.getUsuario().setId(rs.getInt("idusuario"));
				venda.getUsuario().setNome(rs.getString("nome"));
				venda.getUsuario().setLogin(rs.getString("login"));
				venda.getUsuario().setSenha(rs.getString("senha"));
				Date data = rs.getDate("data_nasc");
				venda.getUsuario().setDataNascimento(data == null? null : data.toLocalDate());
				
				listaVenda.add(venda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaVenda;
	}
	
	@Override
	public Venda findById(int id) {
		Venda venda = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.data, ");
		sql.append("  u.id, ");
		sql.append("  u.nome, ");
		sql.append("  u.login,  ");
		sql.append("  u.senha, ");
		sql.append("  u.data_nasc ");					
		sql.append("	FROM	 ");
		sql.append("  public.venda v, ");
		sql.append("  public.usuario u ");
		sql.append("WHERE ");
		sql.append("  v.idusuario = u.id AND ");
		sql.append("  u.id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData( rs.getDate("data").toLocalDate() );
				venda.setUsuario(new Usuario());
				venda.getUsuario().setId(rs.getInt("idusuario"));
				venda.getUsuario().setNome(rs.getString("nome"));
				venda.getUsuario().setLogin(rs.getString("login"));
				venda.getUsuario().setSenha(rs.getString("senha"));
				Date data = rs.getDate("data_nasc");
				venda.getUsuario().setDataNascimento(data == null? null : data.toLocalDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return venda;
	}			

	
	@Override
	public boolean update(Venda venda)  {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

}
