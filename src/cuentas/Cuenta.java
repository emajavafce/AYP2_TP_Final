package cuentas;

import excepciones.banco.*;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private double saldo;
    private String tipo;
    private String alias;
    private final List<Transferencia> transferenciasHechas;
    private final List<Transferencia> transferenciasRecibidas;

    public Cuenta(String alias) {
        this.alias = alias;
        this.transferenciasHechas = new ArrayList<>();
        this.transferenciasRecibidas = new ArrayList<>();
    }

    /**
     * Incrementa el saldo de la cuenta
     */
    public void aumentarSaldo(double monto) throws BancoExcepciones {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        }
        this.saldo += monto;
    }

    /**
     * Disminuye el saldo de la cuenta
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
     */
    public String toString() {
        return this.tipo + "," + this.alias;
    }

    public void guardarTransferenciaHecha(Transferencia transferencia) {
        this.transferenciasHechas.add(transferencia);
    }

    public void guardarTrasferenciaRecibida(Transferencia transferencia) {
        this.transferenciasRecibidas.add(transferencia);
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

}
