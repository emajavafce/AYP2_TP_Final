import dominio.Banco;
import dominio.CajeroATM;
import dominio.Cliente;
import excepciones.NoExisteClienteEx;

public class Pruebas {

    public static void main(String[] args) {
        try {
            Banco banco = new Banco();
            banco.agregarCliente(new Cliente("Ema", "Faillace", "40488107", 29));
            CajeroATM c = new CajeroATM(banco);
            c.menuIngreso();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
