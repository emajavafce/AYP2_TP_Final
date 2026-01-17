package cuentas;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private double saldo;
    private String tipo;
    private String alias;
    private List<Transferencia> transHechas;
    private List<Transferencia> transRecibidas;

    public Cuenta(TipoCuenta tipo, String alias) {
        this.tipo = tipo.getCodigo();
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

    public void aumentarSaldo(double monto) {
        this.saldo += monto;
    }

    public void disminuirSaldo(double monto) {
        this.saldo -= monto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return this.tipo + "," + this.alias + "," + this.saldo;
    }

    public boolean saldoSuficienteParaExtraxion(double monto) {
        return monto <= this.saldo;
    }

}
