package msclientes.common;

import msclientes.domain.Cliente;

public class ClienteConstantes {

    public static final Cliente CLIENTE = new Cliente("03839947561", "nome", 35);
    public static final Cliente INVALID_CLIENTE = new Cliente("0383994756", "", Integer.valueOf(0));
}
