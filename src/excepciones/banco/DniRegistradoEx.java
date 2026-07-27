package excepciones.banco;

public class DniRegistradoEx extends BancoExcepciones {

    public DniRegistradoEx() {
        super("[ERROR] Ya existe un cliente registrado con ese DNI...");
    }
}
