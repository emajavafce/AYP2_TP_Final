import dominio.Banco;
import dominio.CajeroATM;
import dominio.Cliente;

import java.util.Scanner;

public class Pruebas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        try {
            banco.agregarCliente(new Cliente(banco, "Ema", "Faillace", "40488107", 29));
            banco.agregarCliente(new Cliente(banco, "Gaby", "Faillace", "42425058", 26));
            banco.agregarCliente(new Cliente(banco, "Carmen", "Medina", "12770078", 68));
            CajeroATM cajero = new CajeroATM(banco, scanner);
            cajero.encender();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.close();
        }
    }
}
