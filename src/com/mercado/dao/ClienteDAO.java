package com.mercado.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mercado.modelo.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente>{
	
	private List<Cliente> listaCliente = new ArrayList<Cliente>();
	
	public ClienteDAO(){
		super();
		listaCliente = new ArrayList<Cliente>();
	}
	
	public List<Cliente> getLista(Cliente cliente){
		try {
			PreparedStatement ptmt = conn.prepareStatement("select * from Cliente where nome like ?");
			ptmt.setString(1, "%" + cliente.getNome() + "%");
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				listaCliente.add(cliente);
			}
			rs.close();
			ptmt.close();
			} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaCliente;
	}
	
	public List<Cliente> getLista(){
		return listaCliente;
	}
	
	public void adicionar(Cliente cliente){
		try {
			PreparedStatement ptmt = conn.prepareStatement("insert into Cliente (cpf, nome, endereço, dataNascimento) values (?, ?, ?, ?)");
			ptmt.setString(1, cliente.getCpf());
			ptmt.setString(2, cliente.getNome());
			ptmt.setString(3, cliente.getEndereco());
			ptmt.setDate(4, (Date) cliente.getDataNascimento());
			ptmt.executeUpdate();
			ptmt.close();
		} catch (SQLException e) {
    			throw new RuntimeException(e);
		}
		listaCliente.add(cliente);		
	}
	
	public void remover(Cliente cliente){
		/*		try {
		PreparedStatement stmt;
		stmt = conexao.prepareStatement(sql);
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}*/
	}
}