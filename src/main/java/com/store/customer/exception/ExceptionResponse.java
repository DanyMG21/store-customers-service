package com.store.customer.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

	private LocalDateTime fecha;
	private String mensaje;
	private String detalles;

	public ExceptionResponse() {
	}

	public ExceptionResponse(LocalDateTime fecha, String mensaje, String detalles) {
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}

}
