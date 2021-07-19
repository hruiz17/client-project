package co.com.cliente.repository;

import java.util.List;

import co.com.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Cliente (Repository)
 * 
 * @author Humberto Ruiz
 * @since 1.0
 */
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findAllByOrderByNombresAsc();

	public Cliente findByIdTipoDocumentoAndNumeroDocumento(Long aIdTipoDocumento, Long aNumeroDocumento);

	public List<Cliente> findByEdadGreaterThanEqual(Integer aEdad);
}
