package com.capgemini.cursos.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.cursos.model.Cursos;

public interface CursosService {
	//dialogar hacia el frontEnd
	
	List<Cursos> getAllCursos();
	Cursos getCursosbyId(long id);
	void saveCursos(Cursos cursos);
	void deleteCursosbyId(long id);
	
	Page<Cursos> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
