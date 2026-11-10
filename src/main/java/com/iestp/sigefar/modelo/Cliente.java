package com.iestp.sigefar.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nomb_cli", nullable = false, length = 150)
    private String nombCli;

    @Column(name = "ape_cli", nullable = false, length = 150)
    private String apeCli;

    @Column(name = "dni", nullable = false, unique = true, length = 20)
    private String dni;

    @Column(name = "telefono", length = 20)
    private String telefono;

    public Cliente() {
    }

    public Cliente(String nombCli, String apeCli, String dni, String telefono) {
        this.nombCli = nombCli;
        this.apeCli = apeCli;
        this.dni = dni;
        this.telefono = telefono;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombCli() {
        return nombCli;
    }

    public void setNombCli(String nombCli) {
        this.nombCli = nombCli;
    }

    public String getApeCli() {
        return apeCli;
    }

    public void setApeCli(String apeCli) {
        this.apeCli = apeCli;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}


