package cuentas;

public class CajaAhorroPesos extends Cuenta {

    public CajaAhorroPesos(String alias) {
        super(alias);
        super.setTipo("02");
    }

    @Override
    public String mostrarDatosCuenta() {
        return super.mostrarDatosCuenta() + ", $" + this.getSaldo();
    }
}
