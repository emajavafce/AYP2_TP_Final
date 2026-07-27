package excepciones.banco;

public class ClienteYaRegistradoEx extends BancoExcepciones {

    public ClienteYaRegistradoEx() {
        super("[ERROR] Ya existe un cliente registrado con ese DNI...");
    }
}
