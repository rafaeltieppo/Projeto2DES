package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.reidasviagens.viagens.model.Clientes;
import com.reidasviagens.viagens.model.DadosBancarios;

public class ClienteDao {

	private static Connection conectar;
	private static PreparedStatement comando;
	
	public static Clientes cadClienteDao(Clientes cli) {
		conectar = ConnectionBD.getConnection();
		Clientes cliente = new Clientes();
		try {
			String query = "INSERT INTO clientes(id_cliente, nvl, usuario, senha, nome) VALUE(?,?,?,?,?);";

			comando = conectar.prepareStatement(query);

			comando.setInt(1, cli.getIdCliente());
			comando.setInt(2, cli.getNvl());
			comando.setString(3, cli.getUsuario());
			comando.setString(4, cli.getSenha());
			comando.setString(5, cli.getNome());
				
			comando.execute();
			conectar.close();
			
			cliente.setIdCliente(cli.getIdCliente());
			cliente.setNvl(cli.getNvl());
			cliente.setStatus(true);
		} catch (SQLException e) {
			cliente.setStatus(false);
			System.out.println("usuario já existe "+e);
		}
		
		return cliente;
	}
	
	public static int lisClienteBankDao(int idCliente) {
		conectar = ConnectionBD.getConnection();
		int idBancario = 0;
		try {
			String query = "SELECT * FROM clientes where id_cliente="+idCliente+";";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();

			while (rs.next()) {
				idBancario = rs.getInt("id_bancario_cli");
			}
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return idBancario;
	}
	
	public static ArrayList<Clientes> lisClientesDao(int idCliente) {
		conectar = ConnectionBD.getConnection();
		ArrayList<Clientes> cliente = new ArrayList<Clientes>();
		
		try {
			String query = "SELECT * FROM clientes where id_cliente = "+idCliente;
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();

			while (rs.next()) {
				cliente.add(new Clientes(
						rs.getInt(2),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getString(11)
				));
			}
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return cliente;
	}
	// COMPLEMENTO QUANDO FOR COMPRAR
	public static void altComplementoClienteDao(Clientes cli) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "UPDATE clientes SET rg=?, id_bancario_cli=?, sexo=?, "
					+ "endereco=?, telefone=?, nascimento=? WHERE id_cliente=?;";

			comando = conectar.prepareStatement(query);

			comando.setInt(1, cli.getRg());
			comando.setInt(2, cli.getIdBancario().getIdBancario());
			comando.setString(3, cli.getSexo());
			comando.setString(4, cli.getEndereco());
			comando.setInt(5, cli.getTelefone());
			comando.setString(6, cli.getNascimento());
			comando.setInt(7, cli.getIdCliente());
			
			comando.execute();
			conectar.close();
		} catch (SQLException e) {
			System.out.println("usuario já existe "+e);
		}
	}

}
