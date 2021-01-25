package com.sysacad.alkemy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysacad.alkemy.dao.IUsuarioDao;
import com.sysacad.alkemy.entity.Rol;
import com.sysacad.alkemy.entity.Usuario;

@Service
public class JpaUserDetailService implements UserDetailsService{
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsuario(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("No se encontró el usuario " + username);
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		for(Rol rol: usuario.getRoles()) {
			
			roles.add(new SimpleGrantedAuthority(rol.getNombreRol()));

		}
		
		if(roles.isEmpty()) {
			throw new UsernameNotFoundException("El usuario " + username + " no tiene rol/es asignados.");
		}

		return new User(username, usuario.getClave(), usuario.getHabilitado(), true, true, true, roles);
	}

}
