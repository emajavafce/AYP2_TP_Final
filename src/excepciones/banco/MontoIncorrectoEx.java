package excepciones.banco;

public class MontoIncorrectoEx extends BancoExcepciones {

    public MontoIncorrectoEx() {
        super("[ERROR] El monto ingresado debe ser mayor a cero...");
    }
}
