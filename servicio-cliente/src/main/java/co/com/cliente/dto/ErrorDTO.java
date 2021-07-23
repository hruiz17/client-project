package co.com.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {

	@JsonProperty("status_code")
	private Integer statusCode;

	@JsonProperty("message")
	private String message;

	@JsonProperty("tipo")
	private String tipoError;

	@JsonProperty("uri")
	private String uriRequested;

	@JsonProperty("posibilidad_continuar")
	private boolean posibilidadContinuar;

}
