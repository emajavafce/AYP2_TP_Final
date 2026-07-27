package excepciones.banco;

public class CantMaxAliasSuperadaEx  extends BancoExcepciones {

    public CantMaxAliasSuperadaEx() {
        super("[ERROR] Se supero la cantidad maxima de alias posibles...");
    }
}
