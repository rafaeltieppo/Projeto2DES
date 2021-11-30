package com.reidasviagens.viagens.controller;


import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reidasviagens.viagens.dao.AssentosDao;
import com.reidasviagens.viagens.dao.BagagensDao;
import com.reidasviagens.viagens.dao.ClienteDao;
import com.reidasviagens.viagens.dao.DadosBancariosDao;
import com.reidasviagens.viagens.dao.DescontosDao;
import com.reidasviagens.viagens.dao.PassagensDao;
import com.reidasviagens.viagens.model.Assentos;
import com.reidasviagens.viagens.model.Bagagens;
import com.reidasviagens.viagens.model.Clientes;
import com.reidasviagens.viagens.model.DadosBancarios;
import com.reidasviagens.viagens.model.Descontos;
import com.reidasviagens.viagens.model.Passagens;


@Controller
@RestController
public class ClienteProcess {
	
	// Cadastrar Cliente
	@PostMapping("/cadCliente")
	public static Clientes cadCliente(@RequestBody Clientes cliente) {
		return ClienteDao.cadClienteDao(cliente);
	}
	
	@GetMapping("/lisClienteBankDao")
	public static int lisClienteBankDao(int idCliente) {
		return ClienteDao.lisClienteBankDao(idCliente);
	}
	
	@GetMapping("/lisClientesDao")
	public static ArrayList<Clientes> lisClientesDao(int idCliente) {
		return ClienteDao.lisClientesDao(idCliente);
	}
	
	// Cadastrar Cliente sem Dados bancarios
	@PostMapping("/altClienteBank")
	public static void altClienteBank(@RequestBody Clientes cliente) {
		ClienteDao.altComplementoClienteDao(cliente);
	}
	
	@PostMapping("/cadCadastroBank")
	public static void cadCadastroBank(@RequestBody DadosBancarios bank) {
		DadosBancariosDao.cadDadosBanc(bank);
	}
	
	@GetMapping("/lisCadastroBank")
	public static ArrayList<DadosBancarios> lisCadastroBank() {
		return DadosBancariosDao.lisCadastroBanc();
	}
	
	@GetMapping("/lisCadastroBankDados")
	public static ArrayList<DadosBancarios> lisCadastroBankDados(int idCliente) {
		return DadosBancariosDao.lisCadastroDados(idCliente);
	}
	
	@DeleteMapping("/remCadastroBank") 
	public static void remCadastroBank(@RequestBody DadosBancarios bank) {
		DadosBancariosDao.remDadosBanc(bank);
	}

	
	// PASSAGEM
	@PostMapping("/cadPassagem")
	public static void cadPassagem(@RequestBody Passagens passagem) {
		PassagensDao.comprarPassagemDao(passagem);
	}
	
	@GetMapping("/lisPassagem")
	public static ArrayList<Passagens> lisPassagem() {
		return PassagensDao.lisPassagemDao();
	}
	
	
	
	// ASSENTO / BAGAGEM / DESCONTO
	
	@PostMapping("/cadAssento")
	public static void cadAssento(@RequestBody Assentos assento) {
		AssentosDao.cadAssentoDao(assento);
	}
	
	@GetMapping("/lisAssento")
	public static ArrayList<Assentos> lisAssento(@RequestParam int idCard) {
		return AssentosDao.lisAssento(idCard);
	}
	
	@GetMapping("/lisAssentoComp")
	public static ArrayList<Assentos> lisAssentoComp(@RequestParam int idCard) {
		return AssentosDao.lisAssentoComp(idCard);
	}
	
	
	
	@PostMapping("/cadBagagem")
	public static ResponseEntity<Bagagens> cadBagagem(@RequestBody Bagagens bagagem) {
		return BagagensDao.cadBagagemDao(bagagem);
	}
	
	@GetMapping("/lisBagagem")
	public static ArrayList<Bagagens> lisBagagem() {
		return BagagensDao.lisBagagemDao();
	}
	
	
	@PostMapping("/cadDesconto")
	public static void cadDesconto(@RequestBody Descontos desc) {
		DescontosDao.cadDescontoDao(desc);
	}
	
	
	@GetMapping("/lisDesconto")
	public static ArrayList<Descontos> lisDesconto() {
		return DescontosDao.lisDescontoDao();
	}
}
