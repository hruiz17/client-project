package co.com.cliente.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase para el manejo de TipoDocumento (Entidad)
 *
 * @author Humberto Ruiz
 * @since 1.0
 */
@Entity
@Data
@Table(name = "TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable {

	private static final long	serialVersionUID	= -2098610690001491385L;

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long							id;

	@Column(name = "NOMBRE", nullable = false)
	private String						nombre;
}
