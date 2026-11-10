
package com.iestp.sigefar.routers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iestp.sigefar.modelo.Registro;
import com.iestp.sigefar.servicio.RegistroService;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping("/")
    public List<Registro> listarRegistros() {
        return registroService.listarRegistros();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> eliminarHistorial() {
        registroService.eliminarHistorial();
        return ResponseEntity.noContent().build();
    }
}
