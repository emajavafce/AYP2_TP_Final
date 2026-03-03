import cuentas.Cuenta;

import java.util.Scanner;

public class MenuClientes {

    private Scanner scanner;
    private Cliente cliente;

    public MenuClientes(Scanner scanner, Cliente cliente) {
        this.scanner = scanner;
        this.cliente = cliente;
    }

    public void ejecutar() {
        int opcion = 0;
        Cuenta tipoDeCuenta;
        while (opcion != 5) {
            System.out.println("--- Menu de clientes ---\n1-Retirar efectivo\n2-Comprar dolares\n3-Depositar fondos\n4-Hacer transferencia\n5-Salir");
            System.out.print("Ingrese su opcion: ");
            switch (opcion) {
                case 1:
                    this.retirarEfectivo();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("[INFO] Saliendo...");
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente");
                    break;
            }
        }
    }

    private Cuenta elegirCuenta() {
        System.out.println("Seleccione el tipo de cuenta:\n1-Cuenta corriente\n2-Caja de ahorro en pesos\n3-Caja de ahorro en dolares");
        System.out.print("Ingrese su opcion: ");
        int opcion = Integer.parseInt(this.scanner.nextLine());
        switch (opcion) {
            case 1:
                return this.cliente.getCuentaCorriente();
            case 2:
                return this.cliente.getCajaAhorroPesos();
            case 3:
                return this.cliente.getCajaAhorroDolares();
            default:
                System.out.println("[ERROR] La opcion ingresada es incorrecta");
                return null;
        }
    }

    private Cuenta elegirCuentaEnPesos() {
        System.out.println("Seleccione el tipo de cuenta:\n1-Cuenta corriente\n2-Caja de ahorro en pesos");
        System.out.print("Ingrese su opcion: ");
        int opcion = Integer.parseInt(this.scanner.nextLine());
        switch (opcion) {
            case 1:
                return this.cliente.getCuentaCorriente();
            case 2:
                return this.cliente.getCajaAhorroPesos();
            default:
                System.out.println("[ERROR] La opcion ingresada es incorrecta");
                return null;
        }
    }

    private void retirarEfectivo() {
        Cuenta cuenta = this.elegirCuentaEnPesos();
        System.out.print("Ingrese el monto a retirar: ");
        double monto = Double.parseDouble(this.scanner.nextLine());
        if (!Verificador.montoCorrecto(monto)) {
            System.out.println("[ERROR] El monto solicitado es incorrecto");
        } else {
            this.dispensador.
            this.cliente.retirarEfectivo(cuenta, monto);
            System.out.println("El dinero ha sido retirado");
        }

    }
}
