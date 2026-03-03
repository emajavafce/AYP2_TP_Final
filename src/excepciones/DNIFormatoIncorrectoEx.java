package excepciones;

public class DNIFormatoIncorrectoEx extends Exception{

    public DNIFormatoIncorrectoEx(){
        super("[ERROR] El formato del DNI ingresado no es correcto");
    }
}
