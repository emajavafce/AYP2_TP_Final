package dominio;

import cuentas.Cuenta;
import excepciones.DniFormatoIncorrectoEx;
import excepciones.NoExisteClienteEx;
import menus.MenuBanco;
import menus.MenuClientes;

import java.util.Scanner;

public class CajeroATM {

    private DispensadorDinero dispensador;
    private Scanner scanner;
    private Banco banco;
    private MenuBanco menuBanco;
    private MenuClientes menuClientes;

    public CajeroATM() {
        this.dispensador = new DispensadorDinero();
        this.scanner = new Scanner(System.in);
        this.banco = new Banco();
    }

    public CajeroATM(Banco banco) {
        this.dispensador = new DispensadorDinero();
        this.scanner = new Scanner(System.in);
        this.banco = banco;
    }

    public void menuIngreso() throws Exception {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--Menu del cajero ATM--\n1-Ingresar como cliente\n2-Ingresar como banco\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    try {
                        String dni = this.pedirDni();
                        Cliente cliente = this.banco.buscarCliente(dni);
                        new MenuClientes(cliente, this.dispensador, this.scanner).ejecutar();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        new MenuBanco(this.banco, this.scanner).ejecutar();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
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
    public String pedirDni() throws DniFormatoIncorrectoEx {
        System.out.print("Ingrese el DNI: ");
        String dni = this.scanner.nextLine();
        if (!Verificador.dniCorrecto(dni)) {
            throw new DniFormatoIncorrectoEx();
        }
        return dni;
    }

    /*
    Solicita al cliente que eliga el tipo de cuenta que va utilizar
     */
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
