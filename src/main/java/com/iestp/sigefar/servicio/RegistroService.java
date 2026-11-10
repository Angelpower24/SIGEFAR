
package com.iestp.sigefar.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iestp.sigefar.modelo.Registro;

@Service
public class RegistroService {

    private final List<Registro> registros = new ArrayList<>();

    public void agregarRegistro(String modulo, String accion, String informacion) {
        Registro registro = new Registro(modulo, accion, informacion);
        registros.add(0, registro);
    }

    public List<Registro> listarRegistros() {
        return new ArrayList<>(registros);
    }

    public void eliminarHistorial() {
        registros.clear();
    }
}
