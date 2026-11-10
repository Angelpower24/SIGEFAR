package com.iestp.sigefar.servicio;

import com.iestp.sigefar.dao.ClienteRepository;
import com.iestp.sigefar.dao.MedicamentoRepository;
import com.iestp.sigefar.dao.VentaRepository;

import com.iestp.sigefar.modelo.Cliente;
import com.iestp.sigefar.modelo.Medicamento;
import com.iestp.sigefar.modelo.Venta;

import com.iestp.sigefar.servicio.RegistroService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {

	private final VentaRepository ventaRepository;
	private final ClienteRepository clienteRepository;
	private final MedicamentoRepository medicamentoRepository;
	private final RegistroService registroService;

	public VentaService(
	        VentaRepository ventaRepository,
	        ClienteRepository clienteRepository,
	        MedicamentoRepository medicamentoRepository,
	        RegistroService registroService) {
	
	    this.ventaRepository = ventaRepository;
	    this.clienteRepository = clienteRepository;
	    this.medicamentoRepository = medicamentoRepository;
	    this.registroService = registroService;
	}


    public List<Venta> listarPorCliente(Long clienteId) {
        return ventaRepository
                .findByCliente_IdClienteOrderByFechaVentaDesc(clienteId);
    }

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }
    

	public Venta obtenerPorId(Long id) {
	    return ventaRepository.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException(
	                            "Venta no encontrada con ID " + id
	                    )
	            );
	}

    @Transactional
    public Venta registrarVenta(
            Long clienteId,
            Long medicamentoId,
            Integer cantidad) {

        if (cantidad == null || cantidad <= 0) {
            throw new RuntimeException(
                    "La cantidad debe ser mayor a cero"
            );
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado con ID " + clienteId
                        )
                );

        Medicamento medicamento = medicamentoRepository
                .findById(medicamentoId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medicamento no encontrado con ID "
                                        + medicamentoId
                        )
                );

        if (medicamento.getStock() < cantidad) {
            throw new RuntimeException(
                    "Stock insuficiente. Stock disponible: "
                            + medicamento.getStock()
            );
        }

        // Descontar del stock las unidades vendidas.
        medicamento.setStock(medicamento.getStock() - cantidad);

        medicamentoRepository.save(medicamento);

        // Crear la venta con el cliente, medicamento y cantidad.
        Venta venta = new Venta(cliente, medicamento, cantidad);


        // La entidad Venta calcula automáticamente la fecha y el total.
        Venta guardada = ventaRepository.save(venta);

        registroService.agregarRegistro(
                "Ventas",
                "Crear",
                "Se registró una venta para el cliente "
                        + cliente.getNombCli() + " "
                        + cliente.getApeCli()
                        + ", medicamento: " + medicamento.getNombMed()
                        + ", cantidad: " + cantidad
        );

        return guardada;

    }
}
