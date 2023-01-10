package com.capgemini.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.cursos.model.Cursos;
import com.capgemini.cursos.service.CursosService;

@Controller
public class CursosController {
	@Autowired
	private CursosService cursosService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,"nombreCurso", "asc", model);
	}
	
	@GetMapping("/page/{pageNum}")
	public String findPaginated(@PathVariable (value = "pageNum") int pageNum, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 4;
		Page<Cursos> page = cursosService.findPaginated(pageNum, pageSize, sortField, sortDir);
		List<Cursos> listCursos = page.getContent();
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc" :"asc");
		model.addAttribute("listCourses", listCursos);
		
		return "index";
	}
	
	@PostMapping("/save")
	public String saveCurso(@ModelAttribute("course") Cursos cursos) {
		cursosService.saveCursos(cursos);
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String deleteCurso(@PathVariable(value="id") long id) {
		this.cursosService.deleteCursosbyId(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Cursos cursos =cursosService.getCursosbyId(id);
		model.addAttribute("course",cursos);
		return "update_course";
	}
	
	@GetMapping("/add")
	public String showNewCursoForm(Model model) {
		Cursos cursos =new Cursos();
		model.addAttribute("course",cursos);
		return "new_course";
	}
 }
