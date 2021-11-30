package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.reidasviagens.viagens.model.Assentos;
import com.reidasviagens.viagens.model.Cards;

public class AssentosDao {
	private static Connection conectar;
	private static PreparedStatement comando;
	
	
	public static ArrayList<Assentos> lisAssento(int idCard) {
		conectar = ConnectionBD.getConnection();
		ArrayList<Assentos> assentos = new ArrayList<>();

		try {
			
			String query = "SELECT * from assentos where id_card_assento="+idCard+";";
			
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				assentos.add(new Assentos(rs.getInt(1),rs.getInt(3)));		
			}
			
			rs.close();
			conectar.close();
		}catch (SQLException e) {
			System.out.println(e);
		}
		return assentos;
	}
	
	public static ArrayList<Assentos> lisAssentoComp(int idCard) {
		conectar = ConnectionBD.getConnection();
		ArrayList<Assentos> assentos = new ArrayList<>();

		try {
			String query = "SELECT MAX(id_assento) from assentos where id_card_assento ="+idCard+";";
			
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				assentos.add(new Assentos(rs.getInt(1)));		
			}
			
			rs.close();
			conectar.close();
		}catch (SQLException e) {
			System.out.println(e);
		}
		return assentos;
	}
	
	public static void cadAssentoDao(Assentos assento) {
		conectar = ConnectionBD.getConnection();
		
		try {
			String query = "INSERT INTO assentos() VALUE(?,?,?);";
			
			comando = conectar.prepareStatement(query);
			
			comando.setInt(1, assento.getIdAssento());
			comando.setInt(2, assento.getIdCard().getIdCard());
			comando.setInt(3, assento.getAssento());
			
			comando.execute();
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
}
