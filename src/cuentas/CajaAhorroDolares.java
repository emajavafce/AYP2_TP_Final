package cuentas;

public class CajaAhorroDolares extends Cuenta {

    public CajaAhorroDolares(String alias) {
        super(alias);
        this.setTipo("03");
    }

    @Override
    public String toString() {
        return super.toString() + ",U$D" + this.getSaldo();
    }
}
