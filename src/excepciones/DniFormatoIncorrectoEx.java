package excepciones;

public class DniFormatoIncorrectoEx extends Exception{

    public DniFormatoIncorrectoEx(){
        super("[ERROR] El formato del DNI ingresado no es correcto");
    }
}
