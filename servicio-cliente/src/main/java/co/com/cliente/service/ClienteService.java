package co.com.cliente.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.cliente.model.Cliente;
import co.com.cliente.repository.IClienteRepository;
import co.com.cliente.util.IConstantes;

/**
 * Implementación de la interface de servicio de Cliente (Service)
 *
 * @author Humberto Ruiz
 * @since 1.0
 */
@Service
public class ClienteService {

  private IClienteRepository clienteRepository;

  // Metodos publicos

  public Cliente findById(Long aId) {
    return this.clienteRepository.findById(aId).orElse(null);
  }

  public Cliente findByIdClienteAndIdTipoDocumento(Long aIdTipoDocumento, Long aIdNumeroDocumento) {
    return this.clienteRepository.findByIdTipoDocumentoAndNumeroDocumento(aIdTipoDocumento,aIdNumeroDocumento);
  }

  public List<Cliente> findByEdadGreaterThanEqual(Integer aEdad){
    return this.clienteRepository.findByEdadGreaterThanEqual(aEdad);
  }

  public List<Cliente> findAll() {
    return this.clienteRepository.findAllByOrderByNombresAsc();
  }

  @Transactional
  public Cliente createCliente(Cliente aCliente) {
    validateCliente(aCliente, IConstantes.MODO_INSERCION);
    return this.clienteRepository.save(aCliente);
  }

  @Transactional
  public Cliente updateCliente(Cliente aCliente) {
    validateCliente(aCliente, IConstantes.MODO_EDICION);
    Cliente clienteBD = this.clienteRepository.findById(aCliente.getId()).get();
    return this.clienteRepository.save(aCliente);
  }

  @Transactional
  public void deleteCliente(Long aId) {
    Cliente clienteBD = this.clienteRepository.findById(aId).get();
    this.clienteRepository.deleteById(clienteBD.getId());
  }

  // Metodos privados

  private void validateCliente(Cliente aCliente, String aModoTransaction) {
    // Nombre mayuscula sostenida
    aCliente.setNombres(aCliente.getNombres().trim().toUpperCase());

  }

  public String helloWorld() {
    return "Hola Mundo";
  }

  // inyeccion
  @Autowired
  public void setClienteRepository(IClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

}
