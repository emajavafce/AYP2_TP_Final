package excepciones;

public class NoExisteClienteEx extends Exception {

    public NoExisteClienteEx() {
        super("[ERROR] El cliente no existe...");
    }
}
