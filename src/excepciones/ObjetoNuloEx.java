package excepciones;

public class ObjetoNuloEx extends Exception {

    public ObjetoNuloEx() {
        super("[ERROR] El dato a utilizar no debe ser nulo...");
    }
}
