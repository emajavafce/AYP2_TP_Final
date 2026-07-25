package excepciones;

public class EdadIncorrectaEx extends Exception {

    public EdadIncorrectaEx() {
        super("[ERROR] El cliente debe tener una edad mayor a 15 y menor a 121 años...");
    }
}
