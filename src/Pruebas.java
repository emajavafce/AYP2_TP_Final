import dominio.Banco;
import dominio.CajeroATM;
import dominio.Cliente;

public class Pruebas {

    public static void main(String[] args) {
        try {
            Banco banco = new Banco();
            banco.agregarCliente(new Cliente(banco, "Ema", "Faillace", "40488107", 29));
            banco.agregarCliente(new Cliente(banco, "Gaby", "Faillace", "42425058", 26));
            banco.agregarCliente(new Cliente(banco, "Carmen", "Medina", "12770078", 68));
            CajeroATM c = new CajeroATM(banco);
            c.menuIngreso();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
