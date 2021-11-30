package com.reidasviagens.viagens.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.reidasviagens.viagens.dao.CardsDao;
import com.reidasviagens.viagens.dao.CidadesDao;
import com.reidasviagens.viagens.dao.CompanhiasDao;

import com.reidasviagens.viagens.model.Cards;
import com.reidasviagens.viagens.model.Cidades;
import com.reidasviagens.viagens.model.CompanhiaAerea;

@Controller
@RestController
public class AdmProcess {
	

	// CARDS
	@GetMapping("/lisCard")
	public ResponseEntity<ArrayList<Cards>> criarCard(@RequestParam(required = false) String idDes,
			@RequestParam(required = false) String idComp) {
		try {
			if (idDes != null || idComp != null) {
				return new ResponseEntity<ArrayList<Cards>>(CardsDao.lisCardDao(idDes, idComp), HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<ArrayList<Cards>>(CardsDao.lisCardDao(null, null), HttpStatus.ALREADY_REPORTED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * @GetMapping("/lisCardDestino") public ArrayList<Cards> lisCardDestino() {
	 * return CardsDao.lisCardDestino(0); }
	 */

	@PostMapping("/cadCard")
	public ResponseEntity<Cards> criarCard(@RequestBody Cards card) {
		return CardsDao.cadCardDao(card);
	}

	@DeleteMapping("/remCard")
	public void removerCard(@RequestBody Cards card) {
		CardsDao.remCardDao(card);
	}

	/*
	 * @PutMapping("/altCard") public void alterarCard(@RequestBody Cards card) {
	 * CardsDao.altCardDao(card); }
	 */

	// CIDADES
	@GetMapping("/lisCidade")
	public ArrayList<Cidades> lisCidade() {
		return CidadesDao.lisCidadeDao();
	}

	@PostMapping("/cadCidade")
	public ResponseEntity<Cidades> cadCidade(@RequestBody Cidades city) {
		return CidadesDao.cadCidadeDao(city);
	}

	@DeleteMapping("/remCidade")
	public void remCidade(@RequestBody Cidades city) {
		CidadesDao.remCidadeDao(city);
	}

	@PutMapping("/altCidade")
	public void altCidade(@RequestBody Cidades city) {
		CidadesDao.altCidadeDao(city);
	}

	// COMPANHIAS
	@GetMapping("/lisCompanhia")
	public ArrayList<CompanhiaAerea> lisCompanhia() {
		return CompanhiasDao.lisCompanhiaDao();
	}

	@PostMapping("/cadCompanhia")
	public ResponseEntity<CompanhiaAerea> cadCompanhia(@RequestBody CompanhiaAerea companhia) {
		return CompanhiasDao.cadCompanhiaDao(companhia);
	}

	@DeleteMapping("/remCompanhia")
	public void remCompanhia(@RequestBody CompanhiaAerea companhia) {
		CompanhiasDao.remCompanhiaDao(companhia);
	}

	@PutMapping("/altCompanhia")
	public void altCompanhia(@RequestBody CompanhiaAerea companhia) {
		CompanhiasDao.altCompanhiaDao(companhia);
	}

}
