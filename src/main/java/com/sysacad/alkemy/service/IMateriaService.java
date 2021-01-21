package com.sysacad.alkemy.service;

import java.util.List;

import com.sysacad.alkemy.entity.Materia;

public interface IMateriaService {

	public List<Materia> getAll();
	public Materia getOne(Long id);
	public Materia saveOne(Materia materia);
	public void deleteOne(Long id);
}
