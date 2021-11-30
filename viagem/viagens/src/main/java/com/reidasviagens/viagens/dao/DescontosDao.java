package com.reidasviagens.viagens.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.reidasviagens.viagens.model.Descontos;

public class DescontosDao {

	private static Connection conectar;
	private static PreparedStatement comando;

	public static void cadDescontoDao(Descontos desc) {
		conectar =ConnectionBD.getConnection();
		
		try {
			String query = "INSERT INTO descontos VALUE(?,?,?);";
			
			comando = conectar.prepareStatement(query);
			comando.setInt(1, desc.getIdDescontos());
			comando.setString(2, desc.getTipo());
			comando.setDouble(3, desc.getValor());
			
			comando.execute();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);	
		}
	}

	public static ArrayList<Descontos> lisDescontoDao() {
		
		conectar = ConnectionBD.getConnection();
		ArrayList<Descontos> descontos = new ArrayList<Descontos>();
		try {
			String query = "SELECT * FROM descontos;";
			
			comando = conectar.prepareStatement(query);			
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				descontos.add(new Descontos(
						rs.getInt(1),
						rs.getString(2),
						rs.getFloat(3)
				));
			}
			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return descontos;
	}
}
