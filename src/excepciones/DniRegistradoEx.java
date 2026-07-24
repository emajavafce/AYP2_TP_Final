package excepciones;

public class DniRegistradoEx extends Exception {

    public DniRegistradoEx() {
        super("[ERROR] Ya existe un cliente registrado con ese DNI...");
    }
}
