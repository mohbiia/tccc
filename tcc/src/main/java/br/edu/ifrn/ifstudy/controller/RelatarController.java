package br.edu.ifrn.ifstudy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Relatar;

@Controller
//@RequestMapping("/relato")
public class RelatarController {
 //Criei um novo atributo que toda vez que o form for salvo armazena novos dados na classe java
//O ModelMap serve para enviar novas informações
	@GetMapping("/relatar")
	public String relatar (ModelMap model) {
		model.addAttribute("relatar", new Relatar());
		
		return "relatar";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/salvarRel")
	public String salvar (@Valid Relatar relatar, BindingResult result1, RedirectAttributes attr, HttpSession sessao, ModelMap model){
		
		//validando os dados
		if(result1.hasErrors()) {
			model.addAttribute("dados", new Relatar());
			return "relatar";
		}
		
		//Pegando os valores da Sessao
		Integer idM  = (Integer) sessao.getAttribute("idM");
		List<Relatar> dadosCadastrados = 
				(List<Relatar>) sessao.getAttribute("dadosCadastrados");
		
		//contabilizar 
		
		if(idM == null) {
			idM = 1;
		}
		
		if(dadosCadastrados == null) 
			dadosCadastrados = new ArrayList<>();
		
		//verificar se é cadastro ou edição
		if(relatar.getIdRelatar()==0) {
			
			relatar.setIdRelatar(idM);
			dadosCadastrados.add(relatar);
			idM++;
			
			
			sessao.setAttribute("idM", idM);
			sessao.setAttribute("dadosCadastrados", dadosCadastrados);
			
			attr.addFlashAttribute("msgSucesso", "Dados cadastrados com Sucesso");
			
		}else {
			//edição
			dadosCadastrados.remove(relatar);
			dadosCadastrados.add(relatar);
			
			attr.addFlashAttribute("msgSucesso", "Dados alterados com Sucesso");
		}
		
		
		
				
		attr.addFlashAttribute("relatar",  new Relatar());
		
		return "redirect:/relatar";
	}
	
	
		 
		
		
	
}
