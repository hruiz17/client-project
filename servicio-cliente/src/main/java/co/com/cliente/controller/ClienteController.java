package co.com.cliente.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cliente.dto.ClienteDTO;
import co.com.cliente.mappers.ClienteMapper;
import co.com.cliente.model.Cliente;
import co.com.cliente.service.ClienteService;

/**
 * Clase controladora de Cliente (Controller)
 * 
 * @author Humberto Ruiz
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clienteList = this.clienteService.findAll();
		if (clienteList == null || clienteList.isEmpty()) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(
		    clienteList.stream().map(ClienteMapper.INSTANCE::toClienteDTO).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable(value = "id") Long aId) {
		Cliente cliente = this.clienteService.findById(aId);
		if (cliente == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ClienteMapper.INSTANCE.toClienteDTO(cliente), HttpStatus.OK);
	}

	@GetMapping("/tipo-documento/{idTipo}/numero-documento/{idDocumento}")
	public ResponseEntity<ClienteDTO> findByIdTipoDocumentoAndDocumento(@PathVariable(value = "idTipo") Long aIdTipo, @PathVariable(value = "idDocumento") Long aIdDocumento) {
		Cliente cliente = this.clienteService.findByIdClienteAndIdTipoDocumento(aIdTipo, aIdDocumento);
		if (cliente == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ClienteMapper.INSTANCE.toClienteDTO(cliente), HttpStatus.OK);
	}

	@GetMapping("/mayorOIgualA/{edad}")
	public ResponseEntity<List<ClienteDTO>> findByEdadGreaterThan(@PathVariable ("edad") Integer aEdad) {
		List<Cliente> clienteList = this.clienteService.findByEdadGreaterThanEqual(aEdad);
		if (clienteList == null || clienteList.isEmpty()) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(
						clienteList.stream().map(ClienteMapper.INSTANCE::toClienteDTO).collect(Collectors.toList()), HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO aClienteDTO) {
		Cliente clienteSaved = this.clienteService.createCliente(ClienteMapper.INSTANCE.toCliente(aClienteDTO));
		if (clienteSaved == null) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(ClienteMapper.INSTANCE.toClienteDTO(clienteSaved), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ClienteDTO> updateCliente(@Valid @RequestBody ClienteDTO aClienteDTO) {
		if (aClienteDTO.getId() == null)
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		Cliente clienteUpdated = this.clienteService.updateCliente(ClienteMapper.INSTANCE.toCliente(aClienteDTO));
		if (clienteUpdated == null) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(ClienteMapper.INSTANCE.toClienteDTO(clienteUpdated), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCliente(@PathVariable(value = "id") Long aId) {
		this.clienteService.deleteCliente(aId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/hello")
	public ResponseEntity sayHello() {
		return ResponseEntity.ok(this.clienteService.helloWorld());
	}

// **-------------
// * Endpoints
// * -------------
// 
// http://localhost:8080/cliente/api/cliente         ->GET
// 
// http://localhost:8080/cliente/api/cliente         ->POST
//  {
//    "nombres": "PEDRO",
//    "apellidos": "PEREZ",
//    "idTipoDocumento": 1,
//    "numeroDocumento": 94536214,
//    "edad": 24,
//    "idCiudadNacimiento": 1,
//    "foto": null
//  }
//
// http://localhost:8080/cliente/api/cliente         ->PUT
//  {
//    "id": 1,
//    "nombres": "MARIA CLAUDIA",
//    "apellidos": "MONTOYA PEREZ",
//    "idTipoDocumento": 1,
//    "numeroDocumento": 94536214,
//    "edad": 24,
//    "idCiudadNacimiento": 2,
//    "foto": null
//   }
// 
// http://localhost:8080/cliente/api/cliente/{id} ->DELETE 

}
