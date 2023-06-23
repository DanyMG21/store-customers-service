package com.store.customer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 20, message = "firstName debe tener maximo 20 caracteres")
	@Column(nullable = false, length = 20)
	@NotNull(message = "FirstName es requerido")
	private String firstName;

	@Size(max = 20, message = "lastName debe tener maximo 20 caracteres")
	@Column(nullable = false, length = 20)
	@NotNull(message = "LastName es requerido")
	private String lastName;

	@Email(message = "Email formato incorrecto")
	@Column(nullable = false, unique = true)
	private String email;

	@Min(18)
	@Max(100)
	private int age;

	// DEFINE UNA LLAVE FORANEA (RELACION N-1)
	@ManyToOne(fetch = FetchType.LAZY)
	// COLUMNA QUE VA A HACER LA CONEXION CON OTRA TABLA
	@JoinColumn(name = "country_id", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
	@NotNull(message = "country-id es requerido")
	private Country country;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private LocalDateTime deletedAt;

	private String status;

}
