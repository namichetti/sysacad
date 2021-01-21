package com.sysacad.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysacad.alkemy.dao.IProfesorDao;
import com.sysacad.alkemy.entity.Profesor;


@Service
public class ProfesorServiceImpl implements IProfesorService {
	
	@Autowired
	private IProfesorDao profesorDao;

	@Override
	@Transactional(readOnly=true)
	public List<Profesor> getAll() {
		return (List<Profesor>) profesorDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Profesor getOne(Long id) {
		return profesorDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public Profesor saveOne(Profesor profesor) {
		return profesorDao.save(profesor);
	}

	@Override
	@Transactional
	public void deleteOne(Long id) {
		profesorDao.deleteById(id);
	}

}
