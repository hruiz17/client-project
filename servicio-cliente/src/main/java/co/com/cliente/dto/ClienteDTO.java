package co.com.cliente.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO para recibir datos de Cliente (DTO)
 *
 * @author Humberto Ruiz
 */
@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {

  private static final long serialVersionUID = -3559300399907406753L;

  // Atributos
  private Long id;
  @NotBlank
  @Length(max = 60)
  private String nombres;
  @NotBlank
  @Length(max = 60)
  private String apellidos;
  @NotNull
  @Min(1L)
  private Long idTipoDocumento;
  @NotNull
  @Min(1L)
  private Long numeroDocumento;
  @NotNull
  private Integer edad;
  @NotNull
  private Long idCiudadNacimiento;
  private String idFoto;
  private String nombreTipoDocumento;
  private String nombreCiudadNacimiento;
  private String foto;
}
