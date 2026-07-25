import cuentas.Cuenta;
import cuentas.CuentaCorriente;
import cuentas.TipoCuenta;
import excepciones.NoExisteClienteEx;

import java.util.Arrays;
import java.util.List;

public class Pruebas {

    public static void main(String[] args) {
        try {
            CajeroATM c = new CajeroATM();
            c.menuIngreso();
        } catch (NoExisteClienteEx ex) {
            System.out.println(ex);
        }
    }
}
