package co.com.cliente.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para recibir datos de Cliente (DTO)
 * 
 * @author Humberto Ruiz
 *
 */
@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {

	private static final long	serialVersionUID	= -3559300399907406753L;

	// Atributos
	private Long							id;
	private String						nombres;
	private String						apellidos;
	private Long							idTipoDocumento;
	private Long							numeroDocumento;
	private Integer						edad;
	private Long							idCiudadNacimiento;
	private String						idFoto;
	private String						nombreTipoDocumento;
	private String						nombreCiudadNacimiento;
	private String            foto;
}
