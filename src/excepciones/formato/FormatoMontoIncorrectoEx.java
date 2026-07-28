package excepciones.formato;

public class FormatoMontoIncorrectoEx extends FormatoExcepciones {

    public FormatoMontoIncorrectoEx() {
        super("[ERROR] El monto ingresado debe ser un numero, no un simbolo o palabra...");
    }
}
