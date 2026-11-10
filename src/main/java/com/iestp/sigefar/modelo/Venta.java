package com.iestp.sigefar.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamento medicamento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    public Venta() {
    }

    public Venta(Cliente cliente, Medicamento medicamento, Integer cantidad) {
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    @PrePersist
    public void calcularAntesDeGuardar() {
        if (this.fechaVenta == null) {
            this.fechaVenta = LocalDateTime.now();
        }

        if (this.total == null
                && this.medicamento != null
                && this.medicamento.getPrecio() != null
                && this.cantidad != null) {
            this.total = this.medicamento.getPrecio()
                    .multiply(BigDecimal.valueOf(this.cantidad));
        }
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
