import cuentas.Cuenta;
import excepciones.DNIFormatoIncorrectoEx;
import excepciones.NoExisteClienteEx;

import java.util.Scanner;

public class CajeroATM {

    private DispensadorDinero dispensador;
    private Scanner scanner;
    private Banco banco;

    public CajeroATM() {
        this.dispensador = new DispensadorDinero();
        this.scanner = new Scanner(System.in);
        this.banco = new Banco();
    }

    public void menuIngreso() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--Menu del cajero ATM--\n1-Ingresar como cliente\n2-Ingresar como Banco\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    String dni = this.pedirDni();
                    Cliente cliente = this.banco.buscarCliente(dni);
                    if (cliente == null) {
                        System.out.println("[ERROR] No existe el cliente");
                    } else {
                        new MenuClientes(this.scanner, cliente);
                    }
                    break;
                case 2:
                    new MenuBanco(this.scanner, this.banco, this.dispensador);
                    break;

                case 3:
                    this.scanner.close();
                    System.out.println("[INFO] Saliendo...");
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente");
                    break;
            }
        }
    }

    public String pedirDni() {
        while (true) {
            System.out.print("Ingrese su DNI: ");
            String dni = this.scanner.nextLine();
            if (Verificador.dniCorrecto(dni)) {
                return dni;
            }
            System.out.println("[ERROR] El formato del DNI ingresado es incorrecto");
        }
    }


    private Cuenta elegirTipoCuenta() {
        System.out.println("Elija el tipo de cuenta:\n1-Cuenta corriente\n2-Caja de ahorro en pesos\n3-Caja de ahorro en dolares");
        int opcion = Integer.parseInt(this.scanner.nextLine());
        return null;
    }

    public void retirarEfectivo(double montoParaRetirar) {
        this.dispensador.entregarBilletes(montoParaRetirar);
    }

    public void comprarDolares(double monto) {

    }

    public void depositarFondosEn(Cuenta cuenta, double monto) {

    }

    public void realizarTransferencias(Cuenta cuenta, double monto) {

    }
}
