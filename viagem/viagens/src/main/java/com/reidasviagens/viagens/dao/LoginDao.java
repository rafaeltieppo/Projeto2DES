package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.reidasviagens.viagens.model.Adms;
import com.reidasviagens.viagens.model.Clientes;

public class LoginDao {
	private static Connection conectar;
	private static PreparedStatement comando;

	public static Adms loginAdmDao(Adms adm) {

		conectar = ConnectionBD.getConnection();
		Adms admin = new Adms();
		try {
			String query = "SELECT * FROM adm;";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();

			while (rs.next()) {
				String usuario = rs.getString("usuario");
				String senha = rs.getString("senha");
				System.out.println(rs.getInt("nvl"));
				if (adm.getUsuario().equals(usuario) && adm.getSenha().equals(senha)) {
					admin = new Adms(rs.getInt("id_adm"), rs.getInt("nvl"), true);
				}
			}
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return admin;
	}

	public static Clientes loginClienteDao(Clientes cli) {
		conectar = ConnectionBD.getConnection();
		Clientes cliente = new Clientes();
		try {
			String query = "SELECT * FROM clientes;";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			while (rs.next()) {
				String usuario = rs.getString("usuario");
				String senha = rs.getString("senha");
				
				if (cli.getUsuario().equals(usuario) && cli.getSenha().equals(senha)) {
					cliente.setIdCliente(rs.getInt("id_cliente"));
					cliente.setStatus(true);
					cliente.setNvl(1);
					
					if(rs.getString("rg") != null && Integer.parseInt(rs.getString("rg"))  != 0) {
						cliente.setCad(true);
						cliente.setNome(rs.getString("nome"));
					}else {
						cliente.setCad(false);
						cliente.setNome(rs.getString("nome"));
					}
				}
			}
			
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return cliente;
	}
}
