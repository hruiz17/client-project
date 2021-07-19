package co.com.cliente.mappers;

import co.com.cliente.dto.ClienteDTO;
import co.com.cliente.model.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mappers de Cliente (Mapper)
 * 
 * @author Humberto Ruiz
 *
 */
@Mapper
public interface ClienteMapper {
	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

	// Dto's to Entitys

	/**
	 * convierte de DTO Cliente a su entidad
	 * 
	 * @param aClienteDTO
	 * 
	 */
	@Mapping(target = "id", source = "id")
	@Mapping(target = "nombres", source = "nombres")
	@Mapping(target = "apellidos", source = "apellidos")
	@Mapping(target = "idTipoDocumento", source = "idTipoDocumento")
	@Mapping(target = "numeroDocumento", source = "numeroDocumento")
	@Mapping(target = "edad", source = "edad")
	@Mapping(target = "idCiudadNacimiento", source = "idCiudadNacimiento")
	Cliente toCliente(ClienteDTO aClienteDTO);

	// Entitys to DTO's

	/**
	 * Convierte de Cliente a su DTO
	 * 
	 * @param aCliente
	 */
	@InheritInverseConfiguration
	@Mapping(target = "nombreTipoDocumento", source = "tipoDocumento.nombre")
	@Mapping(target = "nombreCiudadNacimiento", source = "ciudad.nombre")
	ClienteDTO toClienteDTO(Cliente aCliente);

}
