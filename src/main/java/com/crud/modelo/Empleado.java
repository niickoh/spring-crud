package com.crud.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;
	@Column(name = "nombre", length = 60, nullable = false)
	@Getter
	@Setter
	private String nombre;
	@Column(name = "apellido", length = 60, nullable = false)
	@Getter
	@Setter
	private String apellido;
	@Column(name = "email", length = 60, nullable = false)
	@Getter
	@Setter
	private String email;

	public Empleado() {
	}

	public Empleado(Long id, String nombre, String apellido, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

}
