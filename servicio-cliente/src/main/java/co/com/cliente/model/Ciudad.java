package co.com.cliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Clase para el manejo de Ciudad (Entidad)
 *
 * @author Humberto Ruiz
 * @since 1.0
 */
@Entity
@Data
@Table(name = "CIUDAD")
public class Ciudad implements Serializable {

  private static final long serialVersionUID = -6784613421222592000L;

  // Atributos
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOMBRE", nullable = false)
  private String nombre;

}
