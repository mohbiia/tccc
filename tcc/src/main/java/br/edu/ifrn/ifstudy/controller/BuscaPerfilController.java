package br.edu.ifrn.ifstudy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Relatar;


@Controller
//@RequestMapping("/usuarios")
public class BuscaPerfilController {


		@GetMapping("/buscaPerfil")
		public String buscarMaterias () {
			return "buscaPerfil";
		}
		
		@SuppressWarnings("unchecked")
		@GetMapping("/buscarRel")
		public String buscar(@RequestParam(name="materia",required= false) String materia, 
				@RequestParam(name="mostrarTudo", required= false)Boolean mostrarTudo, HttpSession sessao
			, ModelMap model) {
			
			//Pegando a lista dos dados cadastrados
			List<Relatar> dadosCadastrados = (List<Relatar>) sessao.getAttribute("dadosCadastrados");
			
			//Lista dos dados encontrados
			
			List<Relatar> dadosEncontrados = new ArrayList<>();
			
			//se o campo de busca estiver vazio, retornará todos os dados inseridos
			if(materia == null || materia.isEmpty()) {
				dadosEncontrados = dadosCadastrados;	
			}else {
				//se tiver digitado e existir dados cadastrados vai pegar os dados de somente a matéria digitada
				if(dadosCadastrados != null) {
					dadosEncontrados =dadosCadastrados.stream().filter(m -> m.getMateria().toLowerCase().contains(materia.toLowerCase())).collect(Collectors.toList());
					}
			}
			
			model.addAttribute("dadosEncontrados", dadosEncontrados);
			
			if(mostrarTudo != null) {
				model.addAttribute("mostrarTudo", true);
			}
			
			
			return "buscaPerfil";
	}
		
		//Editando os dados cadastrados
		@SuppressWarnings("unchecked")
		@GetMapping("/editarRel/{idM}")
		public String iniciarEdicao(@PathVariable ("idM") Integer idMateria,
				ModelMap model, HttpSession sessao ) {
			List<Relatar> dadosCadastrados = 
					(List<Relatar>) sessao.getAttribute("dadosCadastrados");
			
			
			Relatar m = new Relatar();
			m.setIdRelatar(idMateria);
			
			//indexOf pega a posição do elemento
			int pos = dadosCadastrados.indexOf(m);
			m = dadosCadastrados.get(pos);
			
			model.addAttribute("relatar",m);
			
			return "relatar";
		}
		
		@GetMapping("/remover/{idM}")
		public String remover(@PathVariable("idM") Integer idMateria, HttpSession sessao, RedirectAttributes attr) {
			
			// acessando os dados cadastrados na memória
			
			@SuppressWarnings("unchecked")
			List<Relatar> dadosCadastrados = (List<Relatar>) sessao.getAttribute("dadosCadastrados");
			
			Relatar m = new Relatar();
			m.setIdRelatar(idMateria);
			
			boolean removeu = dadosCadastrados.remove(m);
			
			if(removeu) {
				attr.addFlashAttribute("msgSucesso", "Dados removidos");
			}else {
				attr.addFlashAttribute("msgErro", "Não foi possível remover os dados");
			}
			
			return "redirect:/buscarRel";
		}
}
		

