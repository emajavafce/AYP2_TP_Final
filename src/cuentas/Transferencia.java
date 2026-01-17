package cuentas;

public class Transferencia {

    private String aliasEmisor, aliasDestino, motivo;
    private double monto;

    public Transferencia(String aliasEmisor, String aliasDestino, double monto, String motivo) {
        this.aliasEmisor = aliasEmisor;
        this.aliasDestino = aliasDestino;
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
