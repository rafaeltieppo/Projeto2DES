package com.reidasviagens.viagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.reidasviagens.viagens.model.Assentos;
import com.reidasviagens.viagens.model.Bagagens;
import com.reidasviagens.viagens.model.Cards;
import com.reidasviagens.viagens.model.Cidades;
import com.reidasviagens.viagens.model.Clientes;
import com.reidasviagens.viagens.model.CompanhiaAerea;
import com.reidasviagens.viagens.model.Descontos;
import com.reidasviagens.viagens.model.Passagens;

public class PassagensDao {

	private static Connection conectar;
	private static PreparedStatement comando;

	public static void comprarPassagemDao(Passagens passagem) {
		conectar = ConnectionBD.getConnection();

		try {
			String query = "INSERT INTO passagens() VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			comando = conectar.prepareStatement(query);
			
			comando.setInt(1, passagem.getIdPassagem());
			comando.setInt(2, passagem.getIdCard().getIdCard());
			comando.setInt(3, passagem.getIdCliente().getIdCliente());
			comando.setString(4, passagem.getImagePass());
			comando.setInt(5, passagem.getIdAssento().getIdAssento());
			comando.setInt(6, passagem.getIdOrigem().getIdCidade());
			comando.setInt(7, passagem.getIdDestino().getIdCidade());
			comando.setInt(8, passagem.getIdDesconto().getIdDescontos());
			comando.setInt(9, passagem.getCompanhiaAerea().getIdCompanhia());
			comando.setInt(10, passagem.getIdBagagem().getIdBagagem());
			comando.setString(11, passagem.getHoraEmbarque());
			comando.setString(12, passagem.getHoraDesembarque());
			comando.setString(13, passagem.getHoraCompra());
			comando.setFloat(14, passagem.getPreco());

			comando.execute();
			conectar.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static ArrayList<Passagens> lisPassagemDao() {
		conectar = ConnectionBD.getConnection();
		ArrayList<Passagens> pass = new ArrayList<>();

		try {
			String query = "SELECT passagens.*, ad.assento, co.nome, co.aeroporto,"
					+ " co.img, cd.nome, cd.aeroporto,  cd.img, "
					+ "ds.tipo, ds.valor, ca.id_companhia_aerea, ca.nome, ca.cnpj, bg.descricao, bg.peso, bg.custo FROM passagens "
					+ "INNER JOIN assentos as ad ON id_assento_p = ad.id_assento "
					+ "INNER JOIN cidades as co ON id_origem = co.id_cidade "
					+ "INNER JOIN cidades as cd ON id_destino = cd.id_cidade "
					+ "INNER JOIN descontos as ds ON id_desconto_p = ds.id_desconto "
					+ "INNER JOIN companhia_aereas as ca ON id_companhia = ca.id_companhia_aerea "
					+ "INNER JOIN bagagens as bg ON id_bagagem_p = bg.id_bagagem;";

			comando = conectar.prepareStatement(query);
			ResultSet rs = comando.executeQuery();

			while (rs.next()) {

				pass.add(new Passagens(
						rs.getInt(1),
						new Clientes(rs.getInt(3)),
						new Cards(rs.getInt(2)),
						new Assentos(rs.getInt(5), rs.getInt(15)),
						new Cidades(rs.getString(16), rs.getString(17), rs.getString(18)),
						new Cidades(rs.getString(19), rs.getString(20), rs.getString(21)),
						new Descontos(rs.getString(23), rs.getFloat(23)),
						new CompanhiaAerea(rs.getInt(24), rs.getInt(26), rs.getString(25)),
						new Bagagens(rs.getString(27), rs.getFloat(28), rs.getFloat(29)),
						rs.getString(3),
						rs.getString(10),
						rs.getString(11), 
						rs.getString(12), 
						rs.getFloat(14))
				);
			}

			rs.close();
			comando.close();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return pass;
	}
}
