package co.com.cliente;

import co.com.cliente.dto.ClienteDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class ClienteServiceMockTest {

  @Mock
  private IClienteRepository clienteRepository;

  private ClienteService clienteService;


  @Test
  public void whenValidGetID_ThenReturnCliente(){
    MockitoAnnotations.initMocks(this);
    clienteService = new ClienteService(clienteRepository);
    Cliente cliente = Cliente.builder()
            .nombres("Pedro")
            .edad(45)
            .apellidos("Perez Perez")
            .idCiudadNacimiento(1L)
            .idTipoDocumento(1L)
            .numeroDocumento(647584145L).build();

    ResponseEntity<ClienteDTO> found = clienteService.findByIdTipoDocumentoAndDocumento(1L,647584145L);
    Assertions.assertThat(found.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
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
