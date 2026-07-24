package excepciones;

public class ClienteYaRegistradoEx extends Exception {

    public ClienteYaRegistradoEx() {
        super("[ERROR] Ya existe un cliente registrado con ese DNI...");
    }
}
