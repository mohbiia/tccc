package br.edu.ifrn.ifstudy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Usuario;



@Controller
@RequestMapping("/usuarios")
public class ConfigController {

	@GetMapping("/config")
	public String config () {
		return "config";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/buscar")
	public String buscar(
			@RequestParam(name="nome", required=false) String nome, 
			@RequestParam(name="email", required=false) String email, 
			@RequestParam (name= "mostrarTodosDados", required = false) //
				Boolean mostrarTodosDados,
			HttpSession sessao,
			ModelMap model) {
		
		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");
		
		List<Usuario> usuariosEncontrados = new ArrayList<>();
		
		if(nome == null||nome.isEmpty()) {
					// se o usuario nao digitou nada na busca "ou se nome for vazio=empty"
			usuariosEncontrados = usuariosCadastrados;
		}else {
			if (usuariosCadastrados != null) {
					usuariosEncontrados=usuariosCadastrados.stream().
							filter(
								u -> u.getNome().toLowerCase().contains(
											nome.toLowerCase())
										
									
							).collect(Collectors.toList());
											
			}
			
			
		}
		
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}
		
		return "config";
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/remover/{id}")
	public String remover(
				@PathVariable("id") Integer idUsuario,
				HttpSession sessao,
				RedirectAttributes attr
			) {
		
		
		List<Usuario> usuariosCadastrados = 
				(List<Usuario>) sessao.getAttribute("usuariosCadastrados");
		
		Usuario u = new Usuario();
		u.setId(idUsuario);
		
		
		boolean removeu = usuariosCadastrados.remove(u);
		
		if(removeu) {
			attr.addFlashAttribute("msgSucesso", "Usuario removido com sucesso!");
			
		}else {
			attr.addFlashAttribute("msgErro", "Não foi possível remover o usuário.");
		}
		
		
		return "redirect:/usuarios/buscar";
	}
	
}
