
package com.iestp.sigefar.excepciones;

public class DniDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public DniDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
