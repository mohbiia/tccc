package br.edu.ifrn.ifstudy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Usuario;
	
	@Controller
	@RequestMapping("/usuarios")
	public class CadastroUsuarioController {
		
		@GetMapping("/cadastro")
		public String entrarCadastro(ModelMap model) {
			model.addAttribute("usuario", new Usuario());
			return"usuario/cadastro";
		}
		
		@SuppressWarnings("unchecked")
		@PostMapping("/salvar")
		public String Salvar (@Valid Usuario usuario,BindingResult result, 	HttpSession sessao, RedirectAttributes attr, ModelMap model) {			 	 
				
				if(result.hasErrors()) {
				return "usuario/cadastro";
				}		 	 
			
			Integer id =(Integer)sessao.getAttribute("id");
			List <Usuario> usuariosCadastrados = 
					(List<Usuario>) sessao.getAttribute("usuariosCadastrados");
			

			if(id==null) {
				id=1;
			}
			
			if(usuariosCadastrados == null)
				usuariosCadastrados = new ArrayList<>();
			
			if(usuario.getId()== 0) {
				
				usuario.setId(id);
				usuariosCadastrados.add(usuario);
				id++;
					
					//salvando na memória
				sessao.setAttribute("id", id);
				sessao.setAttribute("usuariosCadastrados", usuariosCadastrados); 
				
				attr.addFlashAttribute("msgSucesso", "Cadastro realizado com sucesso");
								
			}
			
							
			
			else {
				//EDIÇÃO
												
				usuariosCadastrados.remove(usuario);
				usuariosCadastrados.add(usuario);
				attr.addFlashAttribute("msgSucesso", "editado com sucesso");
											
			}
			
			
			
			return"inicioPag";		
							
		}
						
		@ModelAttribute("escolaridade")
		public List<String> getEscolaridade(){
			
		return Arrays.asList("Ensino Fundamental", "Ensino Médio", "Superior (graduação)", "outra");
		}
		@SuppressWarnings("unchecked")
		@GetMapping("/editar/{id}")
		public String iniciarEdicao(
				@PathVariable("id") Integer idUsuario,
				ModelMap model,
				HttpSession sessao
				) {
			List<Usuario> usuariosCadastrados =
					(List<Usuario>) sessao.getAttribute("usuariosCadastrados");
			
			Usuario u= new Usuario();
			u.setId(idUsuario);
			
			int pos = usuariosCadastrados.indexOf(u);
			u = usuariosCadastrados.get(pos);
			
			model.addAttribute("usuario", u);
					
			return"/usuario/cadastro";
		}
		
		
	}
