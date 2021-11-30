package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reidasviagens.viagens.model.Bagagens;
import com.reidasviagens.viagens.model.Cidades;

public class BagagensDao {

	private static Connection conectar;
	private static PreparedStatement comando;

	public static ResponseEntity<Bagagens> cadBagagemDao(Bagagens bagagem) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "INSERT INTO bagagens VALUE(?,?,?,?);";

			comando = conectar.prepareStatement(query);

			comando.setInt(1, bagagem.getIdBagagem());
			comando.setString(2, bagagem.getDesc());
			comando.setFloat(3, bagagem.getPeso());
			comando.setFloat(4, bagagem.getValor());

			comando.execute();
			conectar.close();
			
			return new ResponseEntity<Bagagens>(HttpStatus.CREATED);	
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Bagagens>(HttpStatus.BAD_REQUEST);
		}
	}

	public static ArrayList<Bagagens> lisBagagemDao() {
		conectar = ConnectionBD.getConnection();
		ArrayList<Bagagens> bag = new ArrayList<>();

		try {
			String query = "SELECT * FROM bagagens;";
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();

			while (rs.next()) {
				bag.add(new Bagagens(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
			}

			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
		}

		return bag;
	}
}
