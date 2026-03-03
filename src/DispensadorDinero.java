import excepciones.DenominacionIncorrectaEx;
import excepciones.SinStockBilletesEx;

public class DispensadorDinero {

    private final Billete[] billetesEnStock;
    private final int[] denominaciones = {100, 500, 1000};

    public DispensadorDinero() {
        this.billetesEnStock = new Billete[3];
        this.instanciarObjetos();
        this.recargarStockBilletes();
    }

    /**
     * Se instancian los objetos, de tipo Billete, de cada indice
     */
    private void instanciarObjetos() {
        for (int i = 0; i < denominaciones.length; i++) {
            billetesEnStock[i] = new Billete(denominaciones[i], 500);
        }
    }

    /**
     * Recarga diariamente el stock de billetes de cada denominacion.
     * Le asigna una cantidad de 500 unidades para cada uno.
     */
    private void recargarStockBilletes() {
        for (Billete billete : this.billetesEnStock) {
            billete.setCantidad(500);
        }
    }

    /**
     * Descuenta del stock de billetes la cantidad extraida
     *
     * @param denominacion
     * @param cantidadParaExtraer
     */
    private void descontarDelStock(int denominacion, int cantidadParaExtraer) {
        int i = this.getIndiceSegunDenominacion(denominacion);
        int nuevaCantidad = this.billetesEnStock[i].getCantidad() - cantidadParaExtraer;
        this.billetesEnStock[i].setCantidad(nuevaCantidad);
    }

    /**
     * Se obtiene el indice segun la demonimacion del billete
     *
     * @param denominacion
     * @return
     */
    private int getIndiceSegunDenominacion(int denominacion) {
        int i = -1;
        for (int j = 0; j < denominaciones.length; j++) {
            if (denominaciones[j] == denominacion) {
                i = j;
            }
        }
        return i;
    }

    public boolean todaviaHayStockBilletes(double montoParaRetirar) {
        int montoDisponible = 0;

        for (Billete billete : billetesEnStock) {
            montoDisponible += billete.getCantidad() * billete.getDenominacion();
        }

        return montoDisponible >= montoParaRetirar;
    }

    /**
     * Simula el funcionamiento de un cajero automatico al entregar dinero.
     * Los montos solicitados solo puede ser entregados en billetes de 1000, 500 y 100;
     */
    public Billete[] entregarBilletes(double montoParaRetirar) {

        Billete[] billetesParaEntregar = new Billete[3];
        double montoRestante = montoParaRetirar;

        for (int i = 2; i >= 0; i--) {

            int denominacion = denominaciones[i];
            int cantidadDisponible = billetesEnStock[i].getCantidad();

            int cantidadNecesaria = (int) montoRestante / denominacion;
            int cantidadAEntregar = Math.min(cantidadNecesaria, cantidadDisponible);

            if (cantidadAEntregar > 0) {
                billetesParaEntregar[i] = new Billete(denominacion, cantidadAEntregar);
                descontarDelStock(denominacion, cantidadAEntregar);
                montoRestante -= cantidadAEntregar * denominacion;
            } else {
                billetesParaEntregar[i] = new Billete(denominacion, 0);
            }
        }

        return billetesParaEntregar;
    }

    /**
     * Muestra la cantidad de billetes disponibles para cada denominacion
     */
    public void mostrarStockActualDeBilletes() {
        for (Billete billete : this.billetesEnStock) {
            System.out.println("Billetes de $" + billete.getDenominacion() + " : " + billete.getCantidad() + " unidades disponibles");
        }
    }

    public static void main(String[] args) {
        DispensadorDinero d = new DispensadorDinero();
        double montoParaRetirar = 780000;
        Billete[] billetesEntregados = d.entregarBilletes(montoParaRetirar);
        System.out.println("Para $" + montoParaRetirar + " se entrego una cantidad de:");
        for (Billete billete : billetesEntregados) {
            System.out.println("\t" + billete.getCantidad() + " billetes de $ " + billete.getDenominacion());
        }
        d.mostrarStockActualDeBilletes();
        System.out.println(d.todaviaHayStockBilletes(20100));
    }

}
