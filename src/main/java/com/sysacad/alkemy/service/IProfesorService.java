package com.sysacad.alkemy.service;

import java.util.List;

import com.sysacad.alkemy.entity.Profesor;


public interface IProfesorService {
	public List<Profesor> getAll();
	public Profesor getOne(Long id);
	public Profesor saveOne(Profesor profesor);
	public void deleteOne(Long id);
}
