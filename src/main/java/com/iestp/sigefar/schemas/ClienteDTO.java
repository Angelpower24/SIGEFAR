package com.iestp.sigefar.schemas;

import com.iestp.sigefar.modelo.Cliente;

public class ClienteDTO {

    private Long idCliente;
    private String nombCli;
    private String apeCli;
    private String dni;
    private String telefono;

    public static ClienteDTO fromEntity(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.idCliente = cliente.getIdCliente();
        dto.nombCli = cliente.getNombCli();
        dto.apeCli = cliente.getApeCli();
        dto.dni = cliente.getDni();
        dto.telefono = cliente.getTelefono();
        return dto;
    }

    public Long getIdCliente() { return idCliente; }
    public String getNombCli() { return nombCli; }
    public String getApeCli() { return apeCli; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
}
