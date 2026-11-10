
package com.iestp.sigefar.modelo;

import java.time.LocalDateTime;

public class Registro {

    private LocalDateTime hora;
    private String modulo;
    private String accion;
    private String informacion;

    public Registro(String modulo, String accion, String informacion) {
        this.hora = LocalDateTime.now();
        this.modulo = modulo;
        this.accion = accion;
        this.informacion = informacion;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public String getModulo() {
        return modulo;
    }

    public String getAccion() {
        return accion;
    }

    public String getInformacion() {
        return informacion;
    }
}
