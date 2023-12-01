package com.wazaaa.joq.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.wazaaa.joq.interfaceservices.InterfaceCursoService;
import com.wazaaa.joq.interfaceservices.InterfaceDocenteService;
import com.wazaaa.joq.models.Curso;
import com.wazaaa.joq.models.Docente;
import com.wazaaa.joq.models.Usuario;
import com.wazaaa.joq.reports.CursosExporterPDF;
import com.wazaaa.joq.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class CursoController {
	
	@Autowired
	private InterfaceCursoService interfaceCursoService;
	
	@Autowired
	private InterfaceDocenteService interfaceDocenteService;
	
	private final UserService userService;
	
	
	public CursoController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/administrador/cursos")
	public String listarCurso(Model model) {
		List<Curso> cursos = interfaceCursoService.listarCurso();

		
		/*Datos de usuario autenticado*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = userService.getUserByEmail(username);

	

		//Aquí -> addAtribute("nombre de la variable en pagina web", variable interna)
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("cursos", cursos);
		return "cursos";
	}
	
	@GetMapping("/administrador/cursos/nuevoCurso")
	public String agregarCurso(Model model) {
		
		/*Datos de usuario autenticado*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = userService.getUserByEmail(username);
		
		//Aquí -> addAtribute("nombre de la variable en pagina web", variable interna)
		
		List<Docente> docentes = interfaceDocenteService.listarDocente();
		
		model.addAttribute("tit", "Registrar nuevo curso");
		model.addAttribute("colortit", "bg-info-subtle");
		model.addAttribute("docentes", docentes);
		model.addAttribute("usuario", usuario);
		model.addAttribute("curso", new Curso());
		return "formCursos";
	}
	
	@PostMapping("/administrador/cursos/guardar")
	public String guardar(Curso c, Model model) {
		interfaceCursoService.guardarCurso(c);
		return "redirect:/administrador/cursos";
	}
	
	@GetMapping("/administrador/cursos/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		
		/*Datos de usuario autenticado*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = userService.getUserByEmail(username);
		
	    List<Docente> docentes = interfaceDocenteService.listarDocente();

	    // Aquí -> addAtribute("nombre de la variable en pagina web", variable interna)
	    model.addAttribute("tit", "Modificar curso");
	    model.addAttribute("colortit", "bg-warning-subtle");
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("docentes", docentes);

	    // Obtén el curso de la base de datos
	    Optional<Curso> cursoOptional = interfaceCursoService.listarCursoPorId(id);

	    // Verifica si el curso existe antes de agregarlo al modelo
	    if (cursoOptional.isPresent()) {
	        Curso curso = cursoOptional.get();
	        model.addAttribute("curso", curso);
	    } 

	    return "formCursos";
	}
	
	@GetMapping("/administrador/cursos/eliminar/{id}")
	public String eliminar(Model model, @PathVariable int id) {
		interfaceCursoService.eliminarCurso(id);
		return "redirect:/administrador/cursos";
	}
	
	@GetMapping("/administrador/cursos/exportarCursosPDF")
	public void exportarListadoDeCursosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=EscuelaGlobal_Cursos_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Curso> cursos = interfaceCursoService.listarCurso();
		
		CursosExporterPDF exporter = new CursosExporterPDF(cursos);
		exporter.exportarCursos(response);
	}
	
}
