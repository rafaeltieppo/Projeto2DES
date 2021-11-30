package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reidasviagens.viagens.model.Cidades;

public class CidadesDao {

	private static Connection conectar;
	private static PreparedStatement comando;
	
	public static ArrayList<Cidades> lisCidadeDao() {
		ArrayList<Cidades> cidade = new ArrayList<>();
		conectar = ConnectionBD.getConnection();
		
		try {
			String query = "SELECT * FROM cidades;";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				cidade.add(new Cidades(
						rs.getInt("id_cidade"),
						rs.getString("nome"),
						rs.getString("aeroporto"),
						rs.getString("img")
				));
			}
			
			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
		}

		return cidade;
	}

	public static ResponseEntity<Cidades> cadCidadeDao(Cidades city) {
		conectar = ConnectionBD.getConnection();
		try {
			String query = "INSERT INTO cidades() VALUES (?,?,?,?)";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, city.getIdCidade());
			comando.setString(2, city.getNome());
			comando.setString(3, city.getAeroporto());
			comando.setString(4, city.getImage());
			
			comando.execute();
			conectar.close();
			
			return new ResponseEntity<Cidades>(HttpStatus.CREATED);	
		} catch (Exception e) {
			return new ResponseEntity<Cidades>(HttpStatus.BAD_REQUEST);
		}
		
	}

	public static void remCidadeDao(Cidades city) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "DELETE FROM cidades WHERE id_cidade = ?;";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, city.getIdCidade());

			comando.execute();
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void altCidadeDao(Cidades city) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "UPDATE cidades SET nome=?, aeroporto=? WHERE id_cidade=?;";
			comando = conectar.prepareStatement(query);
			comando.setString(1, city.getNome());
			comando.setString(2, city.getAeroporto());
			comando.setInt(3, city.getIdCidade());
			
			comando.execute();
			conectar.close();
		} catch (SQLException e) {

		}
	}
}
