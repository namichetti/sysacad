package com.sysacad.alkemy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysacad.alkemy.dao.IUsuarioDao;
import com.sysacad.alkemy.entity.Materia;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional
	public Materia saveOne(Materia materia) {
		return usuarioDao.save(materia);
	}
	

	

}
