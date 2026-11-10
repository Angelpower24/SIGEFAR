package com.iestp.sigefar.schemas;

import com.iestp.sigefar.modelo.Venta;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaDTO {

    private Long idVenta;
    private String clienteNombre;
    private String medicamentoNombre;
    private Integer cantidad;
    private BigDecimal total;
    private LocalDateTime fechaVenta;

    public static VentaDTO fromEntity(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.idVenta = venta.getIdVenta();
        dto.clienteNombre = venta.getCliente().getNombCli()
                + " " + venta.getCliente().getApeCli();
        dto.medicamentoNombre = venta.getMedicamento().getNombMed();
        dto.cantidad = venta.getCantidad();
        dto.total = venta.getTotal();
        dto.fechaVenta = venta.getFechaVenta();
        return dto;
    }

    public Long getIdVenta() { return idVenta; }
    public String getClienteNombre() { return clienteNombre; }
    public String getMedicamentoNombre() { return medicamentoNombre; }
    public Integer getCantidad() { return cantidad; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
}
