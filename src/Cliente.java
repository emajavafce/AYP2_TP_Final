import cuentas.Cuenta;
import cuentas.TipoCuenta;
import cuentas.Transferencia;

public class Cliente {

    private Cuenta cuentaCorriente, cajaAhorroPesos, cajaAhorroDolares;
    private String nombre, apellido, dni, alias;
    private int edad;

    public Cliente(String nombre, String apellido, String dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
    }

    public String getDni() {
        return this.dni;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String toString() {
        return String.format("Nombre: %s - Apellido: %s - DNI: %s - Edad: %d años", this.nombre, this.apellido, this.dni, this.edad);
    }

    public String mostrarDatosPersonales() {
        return this.toString();
    }

    //public void retirarEfectivo(Cuenta cuenta, double monto) {
    //obtenerCuenta(tipo).disminuirSaldo(monto);
    //}

    public void retirarEfectivo(Cuenta cuenta, double monto) {
        cuenta.disminuirSaldo(monto);
    }

    public void comprarDolares(double monto) {

    }

    public void depositarFondos(TipoCuenta tipo, double monto) {
        this.obtenerCuenta(tipo).aumentarSaldo(monto);
    }

    public void hacerTransferencia(TipoCuenta tipo, String aliasDestino, double monto, String motivo) {
        Cuenta cuenta = this.obtenerCuenta(tipo);
        Transferencia transferencia = new Transferencia(cuenta.getAlias(), aliasDestino, monto, motivo);
        this.obtenerCuenta(tipo).cargarTransferenciaHecha(transferencia);
    }

    public void recibirTransferencia(TipoCuenta tipo, String aliasOrigen, double monto, String motivo) {
        Cuenta cuenta = this.obtenerCuenta(tipo);
        Transferencia transferencia = new Transferencia(aliasOrigen, cuenta.getAlias(), monto, motivo);
        this.obtenerCuenta(tipo).recibirTrasferencia(transferencia);
    }

    private Cuenta obtenerCuenta(TipoCuenta tipo) {
        return switch (tipo) {
            case CUENTA_01 -> this.cuentaCorriente;
            case CUENTA_02 -> this.cajaAhorroPesos;
            case CUENTA_03 -> this.cajaAhorroDolares;
        };
    }

    public Cuenta getCuentaCorriente() {
        return this.cuentaCorriente;
    }

    public Cuenta getCajaAhorroPesos() {
        return this.cajaAhorroPesos;
    }

    public Cuenta getCajaAhorroDolares() {
        return this.cajaAhorroDolares;
    }

}
