package excepciones;

public class FormatoEdadIncorrectoEx extends Exception {

    public FormatoEdadIncorrectoEx() {
        super("[ERROR] La edad debe ser un numero...");
    }
}
