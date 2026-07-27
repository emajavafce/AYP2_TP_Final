package menus;

import cuentas.Cuenta;
import dominio.*;
import excepciones.banco.MontoIncorrectoEx;
import excepciones.banco.MontoSuperiorAlDisponibleEx;
import excepciones.ObjetoNuloEx;

import java.util.Scanner;

public class MenuClientes {

    private Cliente cliente;
    private DispensadorDinero dispensador;
    private Scanner scanner;

    public MenuClientes(Cliente cliente, DispensadorDinero dispensador, Scanner scanner) {
        this.cliente = cliente;
        this.dispensador = dispensador;
        this.scanner = scanner;
    }

    public void ejecutar() throws Exception {
        int opcion = 0;
        String menu = "--- Menu de clientes ---\n1-Retirar efectivo\n2-Comprar dolares\n3-Depositar fondos\n4-Hacer transferencia\n5-Revisar estado de cuentas\n6-Salir";
        Cuenta tipoDeCuenta;
        while (opcion != 5) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    this.retirarEfectivo();
                    System.out.println("El dinero ha sido retirado");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("[INFO] Saliendo del menu de clientes...");
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente");
                    break;
            }
        }
    }

    private Cuenta elegirCuenta() throws NumberFormatException {
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
                return this.elegirCuenta();
        }
    }

    private Cuenta elegirCuentaEnPesos() throws NumberFormatException {
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
                return this.elegirCuentaEnPesos();
        }
    }

    private void retirarEfectivo() throws MontoIncorrectoEx, MontoSuperiorAlDisponibleEx, ObjetoNuloEx {
        Cuenta cuenta = this.elegirCuentaEnPesos();
        System.out.print("Ingrese el monto a retirar: ");
        int monto = Integer.parseInt(this.scanner.nextLine());
        if (!Verificador.montoCorrecto(monto)) {
            throw new MontoIncorrectoEx();
        } else if (!this.dispensador.hayStockSuficiente(monto)) {
            throw new MontoSuperiorAlDisponibleEx();
        } else {
            this.dispensador.entregarBilletes(monto);
            this.cliente.retirarEfectivo(cuenta, monto);
        }

    }
}
