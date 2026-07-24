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
                    if (dni == null) {
                        System.out.println("[ERROR] El formato del DNI ingresado es incorrecto");
                        break;
                    }
                    Cliente cliente = this.banco.buscarCliente(dni);
                    if (cliente == null) {
                        System.out.println("[ERROR] No existe el cliente");
                        break;
                    }
                    new MenuClientes(cliente, this.dispensador, this.scanner);
                    break;
                case 2:
                    new MenuBanco(this.banco, this.dispensador, this.scanner);
                    break;
                case 3:
                    System.out.println("[INFO] Saliendo...");
                    this.scanner.close();
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente");
                    break;
            }
        }
    }

    /*
    Solicita el DNI y verifica que lo ingresado tenga el formato correcto
     */
    public String pedirDni() {
        System.out.print("Ingrese su DNI: ");
        String dni = this.scanner.nextLine();
        return Verificador.dniCorrecto(dni) ? dni : null;
    }

    private Cuenta elegirTipoCuenta() {
        System.out.println("Elija el tipo de cuenta:\n1-Cuenta corriente\n2-Caja de ahorro en pesos\n3-Caja de ahorro en dolares");
        int opcion = Integer.parseInt(this.scanner.nextLine());
        return null;
    }

    public void retirarEfectivo(int montoParaRetirar) {
        this.dispensador.entregarBilletes(montoParaRetirar);
    }

    public void comprarDolares(double monto) {

    }

    public void depositarFondosEn(Cuenta cuenta, double monto) {

    }

    public void realizarTransferencias(Cuenta cuenta, double monto) {

    }
}
