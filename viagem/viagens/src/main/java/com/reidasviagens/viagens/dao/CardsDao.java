package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reidasviagens.viagens.model.Adms;
import com.reidasviagens.viagens.model.Bagagens;
import com.reidasviagens.viagens.model.Cards;
import com.reidasviagens.viagens.model.Cidades;
import com.reidasviagens.viagens.model.CompanhiaAerea;

public class CardsDao {
	private static Connection conectar;
	private static PreparedStatement comando;

	//public static ArrayList<Cards> lisCardDao(String nome, String tipo) {
	public static ArrayList<Cards> lisCardDao(String idDes, String idComp) {
		conectar = ConnectionBD.getConnection();
		ArrayList<Cards> card = new ArrayList<>();

		try {

			String query = "SELECT cards.*, co.nome, co.aeroporto, cd.nome, cd.aeroporto, ca.id_companhia_aerea, ca.nome, ca.cnpj, bg.descricao, bg.peso, bg.custo  FROM cards "
					+ "INNER JOIN cidades as co ON id_origem = co.id_cidade "
					+ "INNER JOIN cidades as cd ON id_destino = cd.id_cidade "
					+ "INNER JOIN companhia_aereas as ca ON id_companhia = ca.id_companhia_aerea "
					+ "INNER JOIN bagagens as bg ON id_bagagem_c = bg.id_bagagem";
			
			query += idComp != null ? " WHERE id_companhia_aerea = "+idComp:"";
			query += idDes != null ? " WHERE id_destino = "+idDes :";";
			
			
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				card.add(new Cards(
						rs.getInt("id_card"),
						new Adms(rs.getInt("id_adm")),
						new Cidades(rs.getInt("id_origem"), rs.getString(18), rs.getString(19)),
						new Cidades(rs.getInt("id_destino"), rs.getString(20), rs.getString(21)),
						new CompanhiaAerea(rs.getInt("id_companhia"),rs.getInt(22), rs.getString(23)), 
						new Bagagens(rs.getInt("id_bagagem_c"), rs.getString(25), rs.getFloat(26), rs.getFloat(27)),
						rs.getString("img"),
						rs.getString("data_ida"),
						rs.getString("data_volta"),
						rs.getString("ih_embarque"),
						rs.getString("ih_desembarque"),
						rs.getString("vh_embarque"),
						rs.getString("vh_desembarque"),
						rs.getFloat("valor"),
						rs.getFloat("pre_adulto"),
						rs.getFloat("pre_crianca"),
						rs.getInt("tot_assentos")
				));
			}
			rs.close();
			conectar.close();
		}catch (SQLException e) {
			System.out.println(e);
		}
		return card;
	}
	
	public static ResponseEntity<Cards> cadCardDao(Cards card) {
		conectar = ConnectionBD.getConnection();
		try {
			String query = "INSERT INTO cards() VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			comando = conectar.prepareStatement(query);
			comando.setInt(1, card.getIdCard());
			comando.setInt(2, card.getIdAdm().getIdAdm());
			comando.setString(3, card.getImageCard());
			comando.setInt(4, card.getIdOrigem().getIdCidade());
			comando.setInt(5, card.getIdDestino().getIdCidade());
			comando.setInt(6, card.getIdBagagens().getIdBagagem());
			comando.setString(7, card.getDataIda());
			comando.setString(8, card.getDataVolta());
			comando.setInt(9, card.getIdCompanhia().getIdCompanhia());
			comando.setString(10, card.getIhEmbarque());
			comando.setString(11, card.getIhDesembarque());
			comando.setString(12, card.getVhEmbarque());
			comando.setString(13, card.getVhDesembarque());
			comando.setFloat(14, card.getValor());
			comando.setFloat(15, card.getPrecoAdulto());
			comando.setFloat(16, card.getPrecoCrianca());
			comando.setInt(17, card.getTotalAssentos());

			comando.execute();
			conectar.close();
				
			return new ResponseEntity<Cards>(HttpStatus.CREATED);
		} catch (SQLException e) {
			return new ResponseEntity<Cards>(HttpStatus.BAD_REQUEST);
		}
		
	}

	public static void remCardDao(Cards card) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "DELETE FROM cards WHERE id_card=?;";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, card.getIdCard());

			comando.execute();
			conectar.close();
		} catch (SQLException e) {

		}
	}
	/*
	public static ArrayList<Cards> lisCardDestino(int id) {
		conectar = ConnectionBD.getConnection();
		ArrayList<Cards> card = new ArrayList<>();
		try {
			String query = "SELECT * FROM cards";
			
			
			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				card.add(new Cards(
						rs.getInt("id_card"),
						new Adms(rs.getInt("id_adm")),
						new Cidades(rs.getInt("id_origem"), rs.getString(18), rs.getString(19)),
						new Cidades(rs.getInt("id_destino"), rs.getString(20), rs.getString(21)),
						new CompanhiaAerea(rs.getInt("id_companhia"),rs.getInt(22), rs.getString(23)), 
						new Bagagens(rs.getInt("id_bagagem_c"), rs.getString(25), rs.getFloat(26), rs.getFloat(27)),
						rs.getString("img"),
						rs.getString("data_ida"),
						rs.getString("data_volta"),
						rs.getString("ih_embarque"),
						rs.getString("ih_desembarque"),
						rs.getString("vh_embarque"),
						rs.getString("vh_desembarque"),
						rs.getFloat("valor"),
						rs.getFloat("pre_adulto"),
						rs.getFloat("pre_crianca"),
						rs.getInt("tot_assentos")
				));
			}
			rs.close();
			conectar.close();
			
		} catch (Exception e) {
			
		}
		
		return card;
	}*/
	
	/*
	public static void altCardDao(Cards card) { 
		conectar = ConnectionBD.getConnection();

		try {
			String query = "UPDATE cards SET tot_assentos = ? WHERE id_card = ?;";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, card.getTotalAssentos());
			comando.setInt(2, card.getIdCard());
			
			comando.execute();
			conectar.close();
		}catch (SQLException e) {

		}
	
	}
	
	
	public static void altCardDao(Cards card) {
		
		conectar = ConnectionBD.getConnection();

		try {
			String query = "UPDATE cards SET id_origem=?, id_destino=?, data_ida=?, data_volta=?, id_companhia=?,ih_embarque=?,"
					+ "ih_desembarque=?,vh_embarque=?, vh_desembarque=?, valor=?,"
					+ "pre_adulto=?, pre_crianca=?, tot_assentos=? WHERE id_card = ?;";
			comando = conectar.prepareStatement(query);
			comando.setInt(1, card.getIdOrigem().getIdCidade());
			comando.setInt(2, card.getIdDestino().getIdCidade());
			comando.setString(3, card.getDataIda());
			comando.setString(4, card.getDataVolta());
			comando.setInt(5, card.getIdCompanhia().getIdCompanhia());
			comando.setString(6, card.getIhEmbarque());
			comando.setString(7, card.getIhDesembarque());
			comando.setString(8, card.getVhEmbarque());
			comando.setString(9, card.getVhDesembarque());
			comando.setFloat(10, card.getValor());
			comando.setFloat(11, card.getPrecoAdulto());
			comando.setFloat(12, card.getPrecoCrianca());
			comando.setInt(13, card.getTotalAssentos());
			comando.setInt(14, card.getIdCard());
			
			comando.execute();
			conectar.close();
		} catch (SQLException e) {

		}
		
	}*/
}
