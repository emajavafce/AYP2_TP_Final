package excepciones;

public class FormatoNombreApellidoIncorrectoEx extends Exception {

    public FormatoNombreApellidoIncorrectoEx() {
        super("[ERROR] El formato ingresado del nombre o apellido es incorrecto...");
    }
}
