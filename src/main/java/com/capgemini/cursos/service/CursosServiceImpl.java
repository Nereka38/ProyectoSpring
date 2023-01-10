package com.capgemini.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.cursos.model.Cursos;
import com.capgemini.cursos.repository.CursosRepository;

@Service //indicamos a Tomcat que es un BEAN a levantar en memoria
public class CursosServiceImpl implements CursosService {
	//dependency injection
	@Autowired
	private CursosRepository cursosRepository;
	
	
	@Override
	public List<Cursos> getAllCursos() {
		return this.cursosRepository.findAll();
	}

	@Override
	public Cursos getCursosbyId(long id) {
		//es lo mismo que: Cursos c = this.cursoRepository.findById(id).orElse(null);
		Optional<Cursos> optionalCursos = this.cursosRepository.findById(id);
		Cursos cursos = null;
		if(optionalCursos.isPresent()) {
			cursos = optionalCursos.get();
		}
		else {
			throw new RuntimeException("el curso no se encuentra nro: " + id);
		}
		return cursos;
	}

	@Override
	public void saveCursos(Cursos cursos) {
		this.cursosRepository.save(cursos);
		
	}

	@Override
	public void deleteCursosbyId(long id) {
		this.cursosRepository.deleteById(id);		
	}

	@Override
	public Page<Cursos> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		//if reducido --> (pregunta logica ? true : false) --> se utiliza cuando lo quiero guardar en una variable
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
				Sort.by(sortField).ascending() :
					Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber -1, pageSize, sort);
		return this.cursosRepository.findAll(pageable);
	}
	
}
