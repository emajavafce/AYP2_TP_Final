package cuentas;

public enum TipoCuenta {

    CUENTA_01("01"),
    CUENTA_02("02"),
    CUENTA_03("03");

    private final String codigo;

    TipoCuenta(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
    
}
