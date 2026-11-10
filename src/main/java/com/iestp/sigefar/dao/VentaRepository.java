package com.iestp.sigefar.dao;

import com.iestp.sigefar.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByCliente_IdClienteOrderByFechaVentaDesc(Long clienteId);
}
