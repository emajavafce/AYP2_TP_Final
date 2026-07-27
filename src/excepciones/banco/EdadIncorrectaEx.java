package excepciones.banco;

public class EdadIncorrectaEx extends BancoExcepciones {

    public EdadIncorrectaEx() {
        super("[ERROR] El cliente debe tener una edad mayor a 15 y menor a 121 años...");
    }
}
