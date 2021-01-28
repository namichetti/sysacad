package com.sysacad.alkemy.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="materias")
public class Materia implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
	private String nombre;
	@Column(name="horario_inicio")
	private LocalTime horarioInicio;
	@Column(name="horario_fin")
	private LocalTime HorarioFin;
	private Integer cupo;
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Column(name="materia_id")
	private List<Profesor> profesores;
	
	private static final long serialVersionUID = 1L;
	
	public Materia() {
		profesores = new ArrayList<Profesor>();
	}
	
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}


	public LocalTime getHorarioFin() {
		return HorarioFin;
	}


	public void setHorarioFin(LocalTime horarioFin) {
		HorarioFin = horarioFin;
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

