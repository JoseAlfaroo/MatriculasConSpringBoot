package com.wazaaa.joq.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wazaaa.joq.models.Usuario;
import com.wazaaa.joq.services.UserService;

@SuppressWarnings("unused")
@Controller
public class HomeController {
	
	private final UserService userService;
	
    public HomeController(UserService userService) {
        this.userService = userService;
    }
	
    @GetMapping({"/usuario/", "/usuario"})
    @PreAuthorize("hasRole('ROLE_USUARIO')")
    public String usuario(Model model){
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	String username = authentication.getName();
    	
    	Usuario usuario = userService.getUserByEmail(username);
    	
    	model.addAttribute("usuario", usuario);
    	
        return "usuario";
    }
    

    @GetMapping({"/administrador/", "/administrador"})
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public String administrador(Model model){
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	String username = authentication.getName();
    	
    	Usuario usuario = userService.getUserByEmail(username);
    	
    	model.addAttribute("usuario", usuario);
    	
        return "administrador";
    }
}
