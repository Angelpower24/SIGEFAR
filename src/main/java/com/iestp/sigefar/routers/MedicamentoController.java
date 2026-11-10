package com.iestp.sigefar.routers;

import com.iestp.sigefar.modelo.Medicamento;
import com.iestp.sigefar.servicio.MedicamentoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    // Listar todos los medicamentos
    @GetMapping
    public List<Medicamento> listar() {
        return medicamentoService.listar();
    }

    // Buscar un medicamento por ID
    @GetMapping("/{id}")
    public Medicamento obtenerPorId(@PathVariable Long id) {
        return medicamentoService.obtenerPorId(id);
    }

    // Registrar un medicamento
    @PostMapping
    public ResponseEntity<Medicamento> crear(
            @RequestBody Medicamento medicamento) {

        Medicamento creado = medicamentoService.crear(medicamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // Actualizar un medicamento
    @PutMapping("/{id}")
    public Medicamento actualizar(
            @PathVariable Long id,
            @RequestBody Medicamento datos) {

        return medicamentoService.actualizar(id, datos);
    }

    // Eliminar un medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    // Manejar errores de tipo RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarError(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
