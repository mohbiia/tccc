package br.edu.ifrn.ifstudy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.ifstudy.dominio.Usuario;

@Controller
public class EditarController {
	

		@GetMapping("/editar")
		public String editar () {
			return "config/editar";
		}
		
		@SuppressWarnings("unchecked")
		@GetMapping("/editar/{email}")
		public String iniciarEdicao(
				@PathVariable("email") String emailUsuario,
				ModelMap model,
				HttpSession sessao
				) {
			
			
			List<Usuario> usuariosCadastrados =
					(List<Usuario>) sessao.getAttribute("usuariosCadastrados");
			Usuario u= new Usuario();
			u.setEmail(emailUsuario);
			
			int posicaoUsuario=-1;
			for(int i= 0;i<usuariosCadastrados.size();i++){
				Usuario usuariozinho=usuariosCadastrados.get(i);
				if(emailUsuario.equals(usuariozinho.getEmail())){
					posicaoUsuario=i;
			 break;

				}
			}
		
			u = usuariosCadastrados.get(posicaoUsuario);
			
			model.addAttribute("usuario", u);
					
			return"/usuario/cadastro";
		}
	
}
