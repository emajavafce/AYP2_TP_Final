package dominio;

import cuentas.*;
import excepciones.banco.*;
import excepciones.objetos.ObjetoNuloEx;

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

    public String datosPersonales() {
        return String.format("Nombre: %s - Apellido: %s - DNI: %s - Edad: %d años", this.nombre, this.apellido, this.dni, this.edad);
    }

    public void retirarDinero(Cuenta cuenta, double monto) throws BancoExcepciones {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        } else if (monto > cuenta.getSaldo()) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        cuenta.disminuirSaldo(monto);
    }

    public void depositarDinero(Cuenta tipoCuenta, double monto) throws BancoExcepciones {
        tipoCuenta.aumentarSaldo(monto);
    }

    public void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, String motivo) {
        Transferencia transferencia = new Transferencia(cuentaOrigen, cuentaDestino, monto, motivo);
        cuentaOrigen.guardarTransferenciaHecha(transferencia);
        cuentaDestino.guardarTrasferenciaRecibida(transferencia);
    }

    public void recibirTransferencia(Cuenta cuentaDestino, Transferencia transferencia) throws BancoExcepciones {
        double montoRecibido = transferencia.getMonto();
        cuentaDestino.aumentarSaldo(montoRecibido);
        cuentaDestino.guardarTrasferenciaRecibida(transferencia);
    }

    private Cuenta obtenerCuenta(TipoCuenta tipo) {
        return switch (tipo) {
            case CUENTA_01 -> cuentaCorriente;
            case CUENTA_02 -> cajaAhorroPesos;
            case CUENTA_03 -> cajaAhorroDolares;
        };
    }

    public void mostrarEstadoDeCuentas() {
        System.out.println("Estado de cuentas:");
        System.out.println("\t" + cuentaCorriente);
        System.out.println("\t" + cajaAhorroPesos);
        System.out.println("\t" + cajaAhorroDolares);
    }

    /**
     * Se obtienen los alias de cada cuenta del cliente
     */
    public String[] getTodosLosAlias() {
        return new String[]{this.cuentaCorriente.getAlias(), this.cajaAhorroPesos.getAlias(), this.cajaAhorroDolares.getAlias()};
    }

    /**
     * Se obtienen todos los alias que tiene el cliente segun cada cuenta
     */
    public String[] getAliasCuentas() {
        return new String[]{cuentaCorriente.getAlias(), cajaAhorroPesos.getAlias(), cajaAhorroDolares.getAlias()};
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

    public String getNombre() {
        return this.nombre;
    }

}
