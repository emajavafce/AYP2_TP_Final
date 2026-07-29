package excepciones.banco;

public class SaldoInsufParaComprarDolaresEx extends BancoExcepciones {

    public SaldoInsufParaComprarDolaresEx() {
        super("[ERROR] El saldo disponible en su cuenta es insuficiente para comprar la cantidad de dolares solicitada...");
    }
}
