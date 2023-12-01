package com.wazaaa.joq.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lowagie.text.DocumentException;
import com.wazaaa.joq.interfaceservices.InterfaceDocenteService;
import com.wazaaa.joq.models.Docente;
import com.wazaaa.joq.models.Usuario;
import com.wazaaa.joq.reports.DocentesExporterPDF;
import com.wazaaa.joq.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
@Controller
@RequestMapping
public class DocenteController {
	
	@Autowired
	private InterfaceDocenteService interfaceDocenteService;
	
	private final UserService userService;
	
	//inicializando usuarioService
	public DocenteController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/administrador/docentes")
	public String listarDocente(Model model) {
		List<Docente> docentes = interfaceDocenteService.listarDocente();
		
		/*Datos de usuario autenticado*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = userService.getUserByEmail(username);
		
		//Aquí -> addAtribute("nombre de la variable en pagina web", variable interna)
		model.addAttribute("usuario", usuario);
		model.addAttribute("docentes", docentes);
		return "docentes";
	}
	
	@GetMapping("/administrador/docentes/nuevoDocente")
	public String agregarDocente(Model model) {
		
		/*Datos de usuario autenticado*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = userService.getUserByEmail(username);
		
		//Aquí -> addAtribute("nombre de la variable en pagina web", variable interna)
		model.addAttribute("tit", "Registrar nuevo docente");
		model.addAttribute("colortit", "bg-info-subtle");
		model.addAttribute("usuario", usuario);
		model.addAttribute("docente", new Docente());
		return "formDocentes";
	}
	
	@PostMapping("/administrador/docentes/guardar")
	public String guardar(Docente d, Model model) {
		interfaceDocenteService.guardarDocente(d);
		return "redirect:/administrador/docentes";
	}
	
	@GetMapping("/administrador/docentes/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		
		/*Datos de usuario autenticado*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = userService.getUserByEmail(username);
		
		//Aquí -> addAtribute("nombre de la variable en pagina web", variable interna)
		model.addAttribute("tit", "Modificar docente");
		model.addAttribute("colortit", "bg-warning-subtle");
		model.addAttribute("usuario", usuario);
		
		Optional<Docente> docente = interfaceDocenteService.listarDocentePorId(id);
		model.addAttribute("docente", docente);
		return "formDocentes";
	}
	
	@GetMapping("/administrador/docentes/eliminar/{id}")
	public String eliminar(Model model, @PathVariable int id) {
		interfaceDocenteService.eliminarDocente(id);
		return "redirect:/administrador/docentes";
	}
	
	@GetMapping("/administrador/docentes/exportarDocentesPDF")
	public void exportarListadoDeDocentesEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=EscuelaGlobal_Docentes_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Docente> docentes = interfaceDocenteService.listarDocente();
		
		DocentesExporterPDF exporter = new DocentesExporterPDF(docentes);
		exporter.exportarDocentes(response);
	}
	
	
}
