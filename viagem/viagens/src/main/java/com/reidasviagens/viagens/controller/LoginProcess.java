package com.reidasviagens.viagens.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.reidasviagens.viagens.dao.LoginDao;
import com.reidasviagens.viagens.model.Adms;
import com.reidasviagens.viagens.model.Clientes;

@Controller
@RestController
public class LoginProcess {

	@PostMapping("/loginAdm")
	public static Adms adm(@RequestBody Adms adm) {
		return LoginDao.loginAdmDao(adm);
	}

	@PostMapping("/loginCli")
	public static Clientes cliente(@RequestBody Clientes cli) {
		return LoginDao.loginClienteDao(cli);
	}
}
