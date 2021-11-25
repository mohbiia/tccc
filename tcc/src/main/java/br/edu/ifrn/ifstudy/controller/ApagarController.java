package br.edu.ifrn.ifstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApagarController {


		@GetMapping("/apagar")
		public String apagar () {
			return "apagar";
		}
	
}
