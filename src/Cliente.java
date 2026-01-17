import cuentas.Cuenta;
import cuentas.TipoCuenta;
import cuentas.Transferencia;

public class Cliente {

    private Cuenta cuentaCorriente, cajaAhorroPesos, cajaAhorroDolares;
    private String nombre, apellido, dni;
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

    public String toString() {
        return String.format("Nombre: %s - Apellido: %s - DNI: %s - Edad: %d años", this.nombre, this.apellido, this.dni, this.edad);
    }

    public String mostrarDatosPersonales() {
        return this.toString();
    }

    public void retirarEfectivo(double monto, TipoCuenta tipo) {
        obtenerCuenta(tipo).disminuirSaldo(monto);
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
}
