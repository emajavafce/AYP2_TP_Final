package excepciones.banco;

public class MontoSuperiorAlDisponibleEx extends BancoExcepciones {

    public MontoSuperiorAlDisponibleEx() {
        super("[ERROR] El monto ingresado es mayor al disponible...");
    }
}
