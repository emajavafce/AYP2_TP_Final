package cuentas;

import excepciones.banco.*;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private double saldo;
    private String tipo;
    private String alias;
    private List<Transferencia> transHechas;
    private List<Transferencia> transRecibidas;

    public Cuenta(String alias) {
        this.alias = alias;
        this.transHechas = new ArrayList<>();
        this.transRecibidas = new ArrayList<>();
    }

    /**
     * Incrementa el saldo de la cuenta
     *
     * @param monto
     * @throws BancoExcepciones
     */
    public void aumentarSaldo(double monto) throws BancoExcepciones {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        }
        this.saldo += monto;
    }

    /**
     * Disminuye el saldo de la cuenta
     *
     * @param monto
     * @throws BancoExcepciones
     */
    public void disminuirSaldo(double monto) throws BancoExcepciones {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        } else if (this.saldo < monto) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        this.saldo -= monto;
    }

    /**
     * Muesta el tipo y alias de una cuentas
     *
     * @return
     */
    public String mostrarDatosCuenta() {
        return this.tipo + "," + this.alias;
    }

    public void cargarTransferenciaHecha(Transferencia transferencia) {
        this.transHechas.add(transferencia);
    }

    public void recibirTrasferencia(Transferencia transferencia) {
        this.transRecibidas.add(transferencia);
    }

    public void setSaldo(int monto) {
        this.saldo = monto;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

}
