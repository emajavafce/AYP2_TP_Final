import excepciones.DenominacionIncorrectaEx;
import excepciones.SinStockBilletesEx;

public class DispensadorDinero {

    private final Billete[] billetesEnStock;
    private static final int[] DENOMINACIONES = {100, 500, 1000};

    public DispensadorDinero() {
        this.billetesEnStock = new Billete[3];
        this.instanciarObjetos();
    }

    /*
     Se instancian los objetos, de tipo Billete, de cada indice
     */
    private void instanciarObjetos() {
        for (int i = 0; i < 3; i++) {
            billetesEnStock[i] = new Billete(this.DENOMINACIONES[i], 500);
        }
    }

    /*
     Recarga diariamente el stock de billetes de cada denominacion.
     Le asigna una cantidad de 500 unidades para cada uno.
     */
    private void recargarStockBilletes() {
        for (Billete billete : this.billetesEnStock) {
            billete.setCantidad(500);
        }
    }

    /*
     Descuenta del stock de billetes la cantidad extraida
     */
    private void descontarDelStock(Billete[] billetesEntregados) {
        int cantBilletes;
        for (int i = 0; i < 3; i++) {
            cantBilletes = this.billetesEnStock[i].getCantidad() - billetesEntregados[i].getCantidad();
            this.billetesEnStock[i].setCantidad(cantBilletes);
        }
    }

    /*
     Se obtiene el indice segun la demonimacion del billete
     */
    private int getIndiceSegunDenominacion(int denominacion) {
        switch (denominacion) {
            case 100:
                return 0;
            case 500:
                return 1;
            case 1000:
                return 2;
            default:
                return -1;
        }
    }

    /*
    Verifica si el cajero puede entregar el monto solicitado
     */
    public boolean hayStockSuficiente(double montoParaRetirar) {
        int montoStock = 0;
        for (Billete billete : this.billetesEnStock) {
            montoStock += billete.getCantidad() * billete.getDenominacion();
        }
        return montoStock >= montoParaRetirar;
    }

    /*
     Simula el funcionamiento de un cajero automatico al entregar dinero.
     Los montos solicitados solo puede ser entregados en billetes de 1000, 500 y 100;
     */
    public Billete[] entregarBilletes(int montoParaRetirar) {
        Billete[] billetesParaEntregar = new Billete[DENOMINACIONES.length];

        int montoRestante = montoParaRetirar;

        for (int i = DENOMINACIONES.length - 1; i >= 0; i--) {
            int denominacion = DENOMINACIONES[i];
            int cantidadDisponible = billetesEnStock[i].getCantidad();

            int cantidadNecesaria = montoRestante / denominacion;
            int cantidadAEntregar = Math.min(cantidadNecesaria, cantidadDisponible);

            billetesParaEntregar[i] = new Billete(denominacion, cantidadAEntregar);

            montoRestante -= cantidadAEntregar * denominacion;
        }
        descontarDelStock(billetesParaEntregar);
        return billetesParaEntregar;
    }

    /*
     Muestra la cantidad de billetes disponibles para cada denominacion
     */
    public void mostrarStockActualDeBilletes() {
        for (Billete billete : this.billetesEnStock) {
            System.out.println("Billetes de $" + billete.getDenominacion() + " -> " + billete.getCantidad() + " unidades disponibles");
        }
    }

}
