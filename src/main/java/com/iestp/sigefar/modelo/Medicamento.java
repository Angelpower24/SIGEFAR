package com.iestp.sigefar.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long idMedicamento;

    @Column(name = "nomb_med", nullable = false, length = 150)
    private String nombMed;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

    public Medicamento() {
    }

    public Medicamento(String nombMed, BigDecimal precio, Integer stock) {
        this.nombMed = nombMed;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombMed() {
        return nombMed;
    }

    public void setNombMed(String nombMed) {
        this.nombMed = nombMed;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
