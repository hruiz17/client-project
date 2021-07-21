package co.com.cliente;

import co.com.cliente.model.Cliente;
import co.com.cliente.repository.IClienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ClienteRepositoryMockTest {

  @Autowired
  private IClienteRepository clienteRepository;

  @Test
  public void whenFindByIdTipoDocumentoAndNumeroDocumento_thenReturnCliente(){
    Cliente cliente = Cliente.builder()
            .nombres("Pedro")
            .edad(45)
            .apellidos("Perez Perez")
            .idCiudadNacimiento(1L)
            .idTipoDocumento(1L)
            .numeroDocumento(647584145L).build();
    clienteRepository.save(cliente);

    Cliente found= clienteRepository.findByIdTipoDocumentoAndNumeroDocumento(1L,647584145L);

    Assertions.assertThat(found);
  }
}