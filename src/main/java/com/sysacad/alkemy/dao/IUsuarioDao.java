package com.sysacad.alkemy.dao;

import org.springframework.data.repository.CrudRepository;

import com.sysacad.alkemy.entity.Materia;
import com.sysacad.alkemy.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsuario(String usuario);

	public Materia save(Materia materia);
	

}
