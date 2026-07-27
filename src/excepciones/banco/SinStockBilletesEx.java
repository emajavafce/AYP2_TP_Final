package excepciones.banco;

public class SinStockBilletesEx extends BancoExcepciones {

    public SinStockBilletesEx() {
        super("No hay stock de billetes para el monto solicitado");
    }
}
