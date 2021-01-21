package com.sysacad.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysacad.alkemy.dao.IMateriaDao;
import com.sysacad.alkemy.entity.Materia;


@Service
public class MateriaServiceImpl implements IMateriaService {
	
	@Autowired
	private IMateriaDao materiaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Materia> getAll() {
		return (List<Materia>)materiaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Materia getOne(Long id) {
		return materiaDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public Materia saveOne(Materia materia) {
		return materiaDao.save(materia);
	}

	@Override
	@Transactional
	public void deleteOne(Long id) {
		materiaDao.deleteById(id);
	}

}

