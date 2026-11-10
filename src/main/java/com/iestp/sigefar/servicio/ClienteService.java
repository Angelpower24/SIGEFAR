
package com.iestp.sigefar.servicio;

import com.iestp.sigefar.dao.ClienteRepository;
import com.iestp.sigefar.modelo.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iestp.sigefar.excepciones.ClienteNoEncontradoException;
import com.iestp.sigefar.excepciones.DniDuplicadoException;

import com.iestp.sigefar.servicio.RegistroService;

import java.util.List;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final RegistroService registroService;

	public ClienteService(
	        ClienteRepository clienteRepository,
	        RegistroService registroService) {
	    this.clienteRepository = clienteRepository;
	    this.registroService = registroService;
	}

    public List<Cliente> listar() {
        return clienteRepository.findAllByOrderByNombCliAsc();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNoEncontradoException(
                                "Cliente no encontrado con ID " + id
                        )
                );
    }

    @Transactional
    public Cliente crear(Cliente cliente) {

        if (clienteRepository.findByDni(cliente.getDni()).isPresent()) {
            throw new DniDuplicadoException(
                    "Ya existe un cliente con el DNI " + cliente.getDni()
            );
        }


		Cliente guardado = clienteRepository.save(cliente);
		
		registroService.agregarRegistro(
		        "Clientes",
		        "Crear",
		        "Se registró el cliente " + guardado.getNombCli()
		                + " " + guardado.getApeCli()
		);
		
		return guardado;

    }

    @Transactional
    public Cliente actualizar(Long id, Cliente datos) {

        Cliente existente = obtenerPorId(id);

        existente.setNombCli(datos.getNombCli());
        existente.setApeCli(datos.getApeCli());
        existente.setTelefono(datos.getTelefono());

        // El DNI no se modifica durante esta actualización.


		Cliente actualizado = clienteRepository.save(existente);
		
		registroService.agregarRegistro(
		        "Clientes",
		        "Actualizar",
		        "Se actualizó el cliente " + actualizado.getNombCli()
		                + " " + actualizado.getApeCli()
		);
		
		return actualizado;

    }

    @Transactional
    public void eliminar(Long id) {

        Cliente existente = obtenerPorId(id);


		String nombreCompleto = existente.getNombCli()
		        + " " + existente.getApeCli();
		
		clienteRepository.delete(existente);
		
		registroService.agregarRegistro(
		        "Clientes",
		        "Eliminar",
		        "Se eliminó el cliente " + nombreCompleto
		);

    }
}
