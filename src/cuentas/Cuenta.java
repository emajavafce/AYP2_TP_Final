package cuentas;

import excepciones.MontoIncorrectoEx;
import excepciones.MontoSuperiorAlDisponibleEx;

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

    public void aumentarSaldo(double monto) throws MontoIncorrectoEx {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        }
        this.saldo += monto;
    }

    public void disminuirSaldo(double monto) throws MontoIncorrectoEx, MontoSuperiorAlDisponibleEx {
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        } else if (this.saldo < monto) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        this.saldo -= monto;
    }

    public String mostrarDatosCuenta() {
        return this.tipo + "," + this.alias + ", " + this.saldo;
    }

}
