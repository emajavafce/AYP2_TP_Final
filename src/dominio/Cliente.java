package dominio;

import cuentas.*;
import excepciones.banco.CantMaxAliasSuperadaEx;
import excepciones.banco.MontoIncorrectoEx;
import excepciones.banco.MontoSuperiorAlDisponibleEx;
import excepciones.ObjetoNuloEx;

public class Cliente {

    private Cuenta cuentaCorriente, cajaAhorroPesos, cajaAhorroDolares;
    private String nombre, apellido, dni;
    private int edad;

    public Cliente(Banco banco, String nombre, String apellido, String dni, int edad) throws CantMaxAliasSuperadaEx {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.cuentaCorriente = new CuentaCorriente(banco.generarAlias());
        this.cajaAhorroPesos = new CajaAhorroPesos(banco.generarAlias());
        this.cajaAhorroDolares = new CajaAhorroDolares(banco.generarAlias());
    }

    public String mostrarDatosPersonales() {
        return String.format("Nombre: %s - Apellido: %s - DNI: %s - Edad: %d años", this.nombre, this.apellido, this.dni, this.edad);
    }

    public void retirarEfectivo(Cuenta cuenta, double monto) throws ObjetoNuloEx, MontoIncorrectoEx, MontoSuperiorAlDisponibleEx {
        if (cuenta == null) {
            throw new ObjetoNuloEx();
        } else if (monto <= 0) {
            throw new MontoIncorrectoEx();
        } else if (monto > cuenta.getSaldo()) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        cuenta.disminuirSaldo(monto);
    }

    public void comprarDolares(double monto) throws MontoIncorrectoEx {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        }

    }

    public void depositarFondos(TipoCuenta tipo, double monto) throws MontoIncorrectoEx {
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

    public void mostrarEstadoDeCuentas() {
        System.out.println("Estado de cuentas:");
        System.out.println("\t" + this.cuentaCorriente.mostrarDatosCuenta());
        System.out.println("\t" + this.cajaAhorroPesos.mostrarDatosCuenta());
        System.out.println("\t" + this.cajaAhorroDolares.mostrarDatosCuenta());
    }

    /**
     * Se obtienen los alias de cada cuenta del cliente
     *
     * @return
     */
    public String[] getAliasDeLasCuentas() {
        return new String[]{this.cuentaCorriente.getAlias(), this.cajaAhorroPesos.getAlias(), this.cajaAhorroDolares.getAlias()};
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

    public String getDni() {
        return this.dni;
    }

}
