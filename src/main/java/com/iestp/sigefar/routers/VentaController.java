package com.iestp.sigefar.routers;

import com.iestp.sigefar.modelo.Venta;
import com.iestp.sigefar.schemas.VentaDTO;
import com.iestp.sigefar.servicio.VentaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Registrar una venta
    @PostMapping
    public ResponseEntity<VentaDTO> registrar(
            @RequestParam Long clienteId,
            @RequestParam Long medicamentoId,
            @RequestParam Integer cantidad) {

        Venta creada = ventaService.registrarVenta(
                clienteId,
                medicamentoId,
                cantidad
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(VentaDTO.fromEntity(creada));
    }

    // Listar las ventas de un cliente
    @GetMapping("/cliente/{clienteId}")
    public List<VentaDTO> listarPorCliente(
            @PathVariable Long clienteId) {

        return ventaService.listarPorCliente(clienteId)
                .stream()
                .map(VentaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Listar todas las ventas
    @GetMapping
    public List<VentaDTO> listarTodas() {
        return ventaService.listarTodas()
                .stream()
                .map(VentaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Manejar errores de tipo RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarError(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    

    // Buscar una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerPorId(
            @PathVariable Long id) {

        Venta venta = ventaService.obtenerPorId(id);

        return ResponseEntity.ok(VentaDTO.fromEntity(venta));
    }

}
