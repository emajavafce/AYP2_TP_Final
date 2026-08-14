package cuentas;

public class Transferencia {

    private String aliasEmisor, aliasDestino, motivo;
    private double monto;

    public Transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, String motivo) {
        this.aliasEmisor = cuentaOrigen.getAlias();
        this.aliasDestino = cuentaDestino.getAlias();
        this.monto = monto;
        this.motivo = motivo;
    }

    public String getAliasEmisor() {
        return aliasEmisor;
    }

    public String getAliasDestino() {
        return aliasDestino;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getMonto() {
        return monto;
    }
}
