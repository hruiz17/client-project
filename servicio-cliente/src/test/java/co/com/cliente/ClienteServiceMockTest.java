package co.com.cliente;

import co.com.cliente.model.Cliente;
import co.com.cliente.repository.IClienteRepository;
import co.com.cliente.service.ClienteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ClienteServiceMockTest {

  @Mock
  private IClienteRepository clienteRepository;

  private ClienteService clienteService;

  @BeforeEach
  public void setup(){
    MockitoAnnotations.initMocks(this);
    clienteService = new ClienteService(clienteRepository);
    Cliente cliente = Cliente.builder()
            .nombres("Pedro")
            .edad(45)
            .apellidos("Perez Perez")
            .idCiudadNacimiento(1L)
            .idTipoDocumento(1L)
            .numeroDocumento(647584145L).build();

    Mockito.when(clienteRepository.findByIdTipoDocumentoAndNumeroDocumento(1L,647584145L))
            .thenReturn(cliente);
  }

  @Test
  public void whenValidGetID_ThenReturnCliente(){
    Cliente found = clienteService.findByIdClienteAndIdTipoDocumento(1L,647584145L);
    Assertions.assertThat(found.getNombres()).isEqualTo("Pedro");
  }

  @Test
  public void whenValidUpdateCiudadCliente_ThenReturnCiudad(){
    Cliente cliente = Cliente.builder()
            .nombres("Pedro")
            .edad(45)
            .apellidos("Perez Perez")
            .idCiudadNacimiento(2L)
            .idTipoDocumento(1L)
            .numeroDocumento(647584145L).build();
    Assertions.assertThat(cliente.getIdCiudadNacimiento()).isEqualTo(2L);
  }



}
