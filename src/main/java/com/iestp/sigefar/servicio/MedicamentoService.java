package com.iestp.sigefar.servicio;

import com.iestp.sigefar.dao.MedicamentoRepository;
import com.iestp.sigefar.modelo.Medicamento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iestp.sigefar.servicio.RegistroService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MedicamentoService {


	private final MedicamentoRepository medicamentoRepository;
	private final RegistroService registroService;
	
	public MedicamentoService(
	        MedicamentoRepository medicamentoRepository,
	        RegistroService registroService) {
	    this.medicamentoRepository = medicamentoRepository;
	    this.registroService = registroService;
	}


    public List<Medicamento> listar() {
        return medicamentoRepository.findAllByOrderByNombMedAsc();
    }

    public Medicamento obtenerPorId(Long id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medicamento no encontrado con ID " + id
                        )
                );
    }

    @Transactional
	public Medicamento crear(Medicamento medicamento) {
	
	    validarPrecio(medicamento.getPrecio());
	    validarStock(medicamento.getStock());
	
	    Medicamento guardado = medicamentoRepository.save(medicamento);
	
	    registroService.agregarRegistro(
	            "Medicamentos",
	            "Crear",
	            "Se registró el medicamento " + guardado.getNombMed()
	    );
	
	    return guardado;
	}

    @Transactional
    public Medicamento actualizar(Long id, Medicamento datos) {

        Medicamento existente = obtenerPorId(id);

        validarPrecio(datos.getPrecio());
        validarStock(datos.getStock());

        existente.setNombMed(datos.getNombMed());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());

        Medicamento actualizado = medicamentoRepository.save(existente);

        registroService.agregarRegistro(
                "Medicamentos",
                "Actualizar",
                "Se actualizó el medicamento " + actualizado.getNombMed()
        );

        return actualizado;
    }

    @Transactional
    public void eliminar(Long id) {

        Medicamento existente = obtenerPorId(id);
        String nombreMedicamento = existente.getNombMed();

        medicamentoRepository.delete(existente);

        registroService.agregarRegistro(
                "Medicamentos",
                "Eliminar",
                "Se eliminó el medicamento " + nombreMedicamento
        );
    }

    private void validarPrecio(BigDecimal precio) {
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "El precio del medicamento debe ser mayor a cero"
            );
        }
    }

    private void validarStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new RuntimeException(
                    "El stock del medicamento no puede ser negativo"
            );
        }
    }
}
