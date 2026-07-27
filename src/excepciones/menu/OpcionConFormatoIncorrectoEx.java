package excepciones.menu;

public class OpcionConFormatoIncorrectoEx extends MenuExcepciones {

    public OpcionConFormatoIncorrectoEx(){
        super("[ERROR] La opcion a ingresar debe ser un numero, no un caracter o palabra...");
    }
}
