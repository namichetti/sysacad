package com.sysacad.alkemy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="materias")
public class Materia implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
	private String nombre;
	private LocalDate horario;
	private Integer cupo;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Column(name="materia_id")
	private List<Profesor> profesores;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="materia_id")
	private List<Usuario> usuarios;
	
	private static final long serialVersionUID = 1L;
	
	public Materia() {
		profesores = new ArrayList<Profesor>();
		usuarios = new ArrayList<Usuario>();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getHorario() {
		return horario;
	}
	public void setHorario(LocalDate horario) {
		this.horario = horario;
	}
	public Integer getCupo() {
		return cupo;
	}
	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

}

