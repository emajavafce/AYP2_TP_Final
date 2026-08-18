package cuentas;

public class CuentaCorriente extends Cuenta {

    private static final double MONTO_DESCUBIERTO = 1000.00;

    public CuentaCorriente(String alias) {
        super(alias);
        super.setTipo("01");
    }

    @Override
    public String toString() {
        return super.toString() + ",$" + this.getSaldo() + ",$" + CuentaCorriente.MONTO_DESCUBIERTO;
    }
}
