package excepciones;

public class MontoSuperiorAlDisponibleEx extends Exception {

    public MontoSuperiorAlDisponibleEx() {
        super("[ERROR] El monto ingresado es mayor al disponible...");
    }
}
