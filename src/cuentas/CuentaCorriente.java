package cuentas;

public class CuentaCorriente extends Cuenta {

    private double saldoDescubierto;

    public CuentaCorriente(TipoCuenta tipo, String alias, double montoDescubierto) {
        super(tipo, alias);
        this.saldoDescubierto = montoDescubierto;
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.saldoDescubierto;
    }
}
