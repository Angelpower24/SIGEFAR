
package com.iestp.sigefar.routers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.iestp.sigefar.modelo.Cliente;
import com.iestp.sigefar.schemas.ClienteDTO;
import com.iestp.sigefar.servicio.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar todos los clientes
    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar()
                .stream()
                .map(ClienteDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Buscar un cliente por ID
    @GetMapping("/{id}")
    public ClienteDTO obtenerPorId(@PathVariable Long id) {
        return ClienteDTO.fromEntity(
                clienteService.obtenerPorId(id)
        );
    }

    // Crear un cliente
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        Cliente creado = clienteService.crear(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody Cliente datos) {

        return clienteService.actualizar(id, datos);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}

