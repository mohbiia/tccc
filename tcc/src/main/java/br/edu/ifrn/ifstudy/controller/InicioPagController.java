package br.edu.ifrn.ifstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


	@Controller
	public class InicioPagController {

		@GetMapping("/inicioPag")
		public String inicioPag () {
			return "inicioPag";
		}
	}


