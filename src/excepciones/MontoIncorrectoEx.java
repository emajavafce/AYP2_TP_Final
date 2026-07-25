package excepciones;

public class MontoIncorrectoEx extends Exception {

    public MontoIncorrectoEx() {
        super("[ERROR] El monto ingresado debe ser mayor a cero...");
    }
}
