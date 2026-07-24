package excepciones;

public class CantMaxAliasSuperadaEx extends Exception {

    public CantMaxAliasSuperadaEx() {
        super("[ERROR] Se supero la cantidad maxima de alias posibles...");

    }
}
