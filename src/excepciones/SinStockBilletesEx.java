package excepciones;

public class SinStockBilletesEx extends Exception{

    public SinStockBilletesEx(){
        super("No hay stock de billetes para el monto solicitado");
    }
}
