package com.capgemini.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.cursos.model.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long> {
	//dialoga hacia la DB
}