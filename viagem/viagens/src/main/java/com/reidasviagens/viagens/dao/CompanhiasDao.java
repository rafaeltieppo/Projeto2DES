package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reidasviagens.viagens.model.CompanhiaAerea;

public class CompanhiasDao {

	private static Connection conectar;
	private static PreparedStatement comando;

	public static ArrayList<CompanhiaAerea> lisCompanhiaDao() {
		conectar = ConnectionBD.getConnection();
		ArrayList<CompanhiaAerea> companhia = new ArrayList<>();

		try {
			String query = "SELECT * FROM companhia_aereas;";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();

			while (rs.next()) {
				companhia.add(
						new CompanhiaAerea(rs.getInt("id_companhia_aerea"), rs.getInt("cnpj"), rs.getString("nome")));
			}

			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
		}

		return companhia;
	}

	public static ResponseEntity<CompanhiaAerea> cadCompanhiaDao(CompanhiaAerea companhia) {
		conectar = ConnectionBD.getConnection();
		System.out.println(companhia.getCnpj());
		try {
			String query = "INSERT INTO companhia_aereas() VALUE (?,?,?)";
			comando = conectar.prepareStatement(query);

			comando.setInt(1, companhia.getIdCompanhia());
			comando.setInt(2, companhia.getCnpj());
			comando.setString(3, companhia.getNome());
			
			
			comando.execute();
			conectar.close();
			
			return new ResponseEntity<CompanhiaAerea>(HttpStatus.CREATED);
		} catch (SQLException e) {
			System.out.println(e);
			return new ResponseEntity<CompanhiaAerea>(HttpStatus.BAD_REQUEST);
		}

	}

	public static void remCompanhiaDao(CompanhiaAerea companhia) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "DELETE FROM companhia_aereas WHERE id_companhia_aerea = ?;";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, companhia.getIdCompanhia());

			comando.execute();
			conectar.close();
		} catch (SQLException e) {

		}
	}

	public static void altCompanhiaDao(CompanhiaAerea companhia) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "UPDATE companhia_aereas SET cnpj=?, nome=? WHERE id_companhia_aerea=?;";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, companhia.getCnpj());
			comando.setString(2, companhia.getNome());
			comando.setInt(3, companhia.getIdCompanhia());

			comando.execute();
			conectar.close();
		} catch (SQLException e) {

		}
	}

}
