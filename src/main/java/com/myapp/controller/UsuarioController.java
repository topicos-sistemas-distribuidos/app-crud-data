package com.myapp.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.modelo.Usuario;
import com.myapp.services.UsuarioService;

@Controller
public class UsuarioController {
private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioService usuarioService;
		
	/**
	 * Contrutor
	 */
	public UsuarioController(){
	}
	
	/**
	 * Faz a busca de usuários
	 * @param request HttpServletRequest do usuário 
	 * @param model Model da aplicação
	 * @return jsp do ModelAndView da aplicação
	 * @throws ServletException exceção do tipo Servlet
	 * @throws IOException exceção do tipo IOException
	 */
	@RequestMapping(value="/buscarUsuario", method=RequestMethod.POST)
	protected String processarBusca(HttpServletRequest request, Model model) {
		String tipo = request.getParameter("opcaotipo");
		String conteudo = request.getParameter("conteudobusca");
		List<Usuario> usuarios=null; 
		
		if (!conteudo.equals("")){
			usuarios = usuarioService.buscarPorConteudo(conteudo, tipo);	
			if (usuarios != null) {
				Usuario usuario = usuarios.get(0); //pega apenas o primeiro
				model.addAttribute("usuario", usuario);
			} else {
				model.addAttribute("mensagem", "Não retornou nenhum resultado!");
			}
		}else{
			model.addAttribute("mensagem", "Conteúdo vazio...");	
		}
		
		return "/resultadoBusca";
	}
	
	/**
	 * Carrega o formulário de busca de usuários
	 * @return página TelaBuscarUsuario.jsp | Home.jsp
	 */
	@RequestMapping(value = "/buscarUsuario", method = RequestMethod.GET)
	public String carregaFormularioBusca() {
		return "/buscarUsuario";
	}
	
	/**
	 * Lista os usuários da aplicação
	 * @param model Model da aplicação
	 * @return página TelaListarUsuarios.jsp | Home.jsp
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/listarUsuarios", method=RequestMethod.GET)
	public String processarListaUsuarios(Model model) {
		List<Usuario> lista = usuarioService.listar();

		if (lista.size() > 0 ){
			model.addAttribute("usuarios", lista);
		}else{
			model.addAttribute("mensagem", "Não há usuários cadastrados no sistema");
		}
		return "/listarUsuarios";
	}

	/**
	 * Faz a alteração dos dados de um usuário
	 * @param usuario Usuario da aplicação
	 * @param redirect RedirectAttributes
	 * @return página TelaListarUsuarios.jsp | Home.jsp
	 * @throws ServletException trata a exceção ServletException caso aconteça
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/alterarUsuario", method=RequestMethod.POST)
	public String processarAlterarUsuario(String papel, Usuario usuario, RedirectAttributes redirectAttribute) {
		String senhaOriginal;
		senhaOriginal = usuario.getSenha();

		usuarioService.alterar(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getEmail(), usuario.getSenha(), usuario.getImagemPath());
		redirectAttribute.addFlashAttribute("mensagem", "Usuário " + usuario.getNome() + " alterado com sucesso!");
		
		return "redirect:/listarUsuarios";
	}

	/**
	 * Carrega o formulário Alterar Usuário
	 * @return página TelaAlterarUsuario.jsp | Home.jsp
	 */
	@RequestMapping("/alterarUsuario")
	public ModelAndView carregaFormularioAlterar(int id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		
		ModelAndView mav = new ModelAndView("/alterarUsuario");
		mav.addObject("usuario", usuario);
		
		return (mav);
	}

	/**
	 * Carrega o formulário Inserir Usuário
	 * @return página TelaInserirUsuario.jsp | Home.jsp
	 */
	@RequestMapping(value="/inserirUsuario", method = RequestMethod.GET)
	public String carregarFormularioInserir(Model model) {
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("papel", "papel");
		
		return "/inserirUsuario";
	}

	/**
	 * Insere usuário
	 * @param usuario Dados do Usuário
	 * @param redirect RedirectAttributes mensagem de redirect
	 * @return página TelaPrincipal.jsp | Home.jsp
	 * @throws ServletException 
	 * @throws IOException
	 */
	@RequestMapping(value="/inserirUsuario", params={"save"}, method=RequestMethod.POST)
	public String processarInserirUsuario(@Valid Usuario usuario, BindingResult bindingResult, final String papel, RedirectAttributes redirectAttribute){
		String senhaOriginal; 
		
		if (bindingResult.hasErrors()) {
			return "/inserirUsuario";
		}
		
		senhaOriginal = usuario.getSenha();
				 
		usuarioService.inserir(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuario inserido com sucesso!");
		
		return "redirect:/listarUsuarios";
	}
	
	/**
	 * Mostra detalhes do usuário selecionado
	 * @param id do usuário
	 * @return view TelaDetalhesUsuario.jsp | Home.jsp
	 */
	@RequestMapping("/detalharUsuario/{id}")
	public ModelAndView processarDetalhesUsuario(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("/detalharUsuario");
		
		Usuario usuario = usuarioService.buscarPorId(id);
		mav.addObject("usuario", usuario);
		
		return mav;
	}

	/**
	 * Remove o usuáro seleconado
	 * @param id do usuário selecionado
	 * @param redirectAttribute mensagem do tipo flash para evitar repetição de post
	 * @return view listaUsuários | Home.jsp
	 */
	@RequestMapping("/removerUsuario/{id}")
	public String processarRemoverUsuario(@PathVariable("id") int id, RedirectAttributes redirectAttribute) {
		Usuario usuario = usuarioService.buscarPorId(id);
		
		usuarioService.remover(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuario " + id + " removido com sucesso!");
		
		return "redirect:/listarUsuarios";
	}
	
	@RequestMapping("/meuperfil/{id}")
	public ModelAndView processarPerfilUsuario(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView("/perfilUsuario");
		//TODO conclui a funcionalidade de exibição do perfil do usuário
		return mav;
	}

}