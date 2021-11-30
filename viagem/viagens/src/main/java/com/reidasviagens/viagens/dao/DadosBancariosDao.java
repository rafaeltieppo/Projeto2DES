package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.reidasviagens.viagens.model.DadosBancarios;

public class DadosBancariosDao {
	
	private static Connection conectar;
	public static PreparedStatement comando;
	
	
	public static ArrayList<DadosBancarios> lisCadastroBanc() {
		ArrayList<DadosBancarios> bank = new ArrayList<>();
		conectar = ConnectionBD.getConnection();
		try {
			String query = "SELECT MAX(id_bancario) from dados_bancarios;";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				bank.add(new DadosBancarios(
						rs.getInt(1)
				));
			}
			
			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return bank;
	}
	
	public static ArrayList<DadosBancarios> lisCadastroDados(int idCliente) {
		ArrayList<DadosBancarios> bank = new ArrayList<>();
		conectar = ConnectionBD.getConnection();
		try {
			String query =
					 "SELECT clientes.id_bancario_cli, dd.nome_cli_cartao, dd.numero_cartao FROM clientes "
					+ "INNER JOIN dados_bancarios as dd on id_bancario = clientes.id_bancario_cli  where clientes.id_cliente = "+idCliente+";";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				bank.add(new DadosBancarios(
						rs.getInt(1),
						rs.getInt(3),
						rs.getString(2)
				));
			}
			
			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return bank;
	}
	
	public static void cadDadosBanc(DadosBancarios bank) {
		conectar = ConnectionBD.getConnection();
		
		try {
			String query = "INSERT INTO dados_bancarios VALUE (?,?,?,?,?,?);";
			
			comando = conectar.prepareStatement(query);
			comando.setInt(1, bank.getIdBancario());
			comando.setInt(2, bank.getNumeroCartao());
			comando.setString(3, bank.getNomeCliCartao());
			comando.setString(4, bank.getValidade());
			comando.setInt(5, bank.getNumeroConta());
			comando.setInt(6, bank.getChave());
			
			comando.execute();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void remDadosBanc(DadosBancarios bank) {
		conectar = ConnectionBD.getConnection();
		
		try {
			String query = "DELETE FROM dados_bancarios WHERE id_bancario=?";
			
			comando = conectar.prepareStatement(query);
			comando.setInt(1, bank.getIdBancario());
			
			comando.execute();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
