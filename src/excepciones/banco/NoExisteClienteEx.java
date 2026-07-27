package excepciones.banco;

public class NoExisteClienteEx extends BancoExcepciones {

    public NoExisteClienteEx() {
        super("[ERROR] El cliente no existe...");
    }
}
