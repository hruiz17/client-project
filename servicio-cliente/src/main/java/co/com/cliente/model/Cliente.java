package co.com.cliente.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import co.com.cliente.model.TipoDocumento;

/**
 * Clase para el manejo de Cliente (Entidad)
 *
 * @author Humberto Ruiz
 * @since 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "CLIENTE",
        indexes = {@Index(name = "identificacion", columnList = "ID_TIPO_DOCUMENTO,NUMERO_DOCUMENTO", unique = true)}
)

public class Cliente implements Serializable {

  private static final long serialVersionUID = -254747720094795954L;

  // Atributos
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOMBRES", nullable = false)
  private String nombres;

  @Column(name = "APELLIDOS", nullable = false)
  private String apellidos;

  @Column(name = "ID_TIPO_DOCUMENTO", nullable = false)
  private Long idTipoDocumento;

  @Column(name = "NUMERO_DOCUMENTO", nullable = false)
  private Long numeroDocumento;

  @Column(name = "EDAD", nullable = false)
  private Integer edad;

  @Column(name = "ID_CIUDAD_NACIMIENTO", nullable = false)
  private Long idCiudadNacimiento;

  @Column(name = "ID_FOTO")
  private String idFoto;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_TIPO_DOCUMENTO", insertable = false, updatable = false)
  private TipoDocumento tipoDocumento;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_CIUDAD_NACIMIENTO", insertable = false, updatable = false)
  private Ciudad ciudad;

}
