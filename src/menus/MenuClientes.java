package menus;

import cuentas.Cuenta;
import dominio.*;
import excepciones.banco.*;
import excepciones.formato.*;
import excepciones.menu.*;
import excepciones.objetos.ObjetoNuloEx;

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
        while (opcion != 5) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    this.retirarEfectivo();
                    System.out.println("[INFO] El dinero ha sido retirado!");
                    break;
                case 2:

                    break;
                case 3:
                    this.depositarFondos();
                    System.out.println("[INFO] El dinero ha sido depositado!");
                    break;
                case 4:
                    break;
                case 5:
                    this.cliente.mostrarEstadoDeCuentas();
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

    private void retirarEfectivo() throws FormatoExcepciones, BancoExcepciones {
        Cuenta cuenta = this.elegirCualCuentaEnPesos();
        System.out.print("Ingrese el monto a retirar: ");
        int monto;
        try {
            monto = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        if (!this.dispensador.hayStockSuficiente(monto)) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        this.dispensador.entregarBilletes(monto);
        this.cliente.retirarDinero(cuenta, monto);
    }

    private void depositarDolares() throws FormatoExcepciones, BancoExcepciones {
        System.out.print("Ingrese el monto a depositar: ");
        int monto;
        try {
            monto = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new FormatoMontoIncorrectoEx();
        }
        this.cliente.depositarDinero(this.cliente.getCajaAhorroDolares(), monto);
    }

    private void depositarFondos() throws MenuExcepciones, FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaDestino = this.elegirCuenta();
        int monto;
        try {
            System.out.print("Ingrese el monto a depositar: ");
            monto = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        this.cliente.depositarDinero(cuentaDestino, monto);
    }

    private Cuenta elegirCuenta() throws MenuExcepciones {
        System.out.println("Seleccione el tipo de cuenta:\n1-Cuenta corriente\n2-Caja de ahorro en pesos\n3-Caja de ahorro en dolares");
        System.out.print("Ingrese su opcion: ");
        int opcion;
        try {
            opcion = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new OpcionConFormatoIncorrectoEx();
        }
        switch (opcion) {
            case 1:
                return this.cliente.getCuentaCorriente();
            case 2:
                return this.cliente.getCajaAhorroPesos();
            case 3:
                return this.cliente.getCajaAhorroDolares();
            default:
                System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
                return this.elegirCuenta();
        }
    }

    private Cuenta elegirCualCuentaEnPesos() throws NumberFormatException {
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
                return this.elegirCualCuentaEnPesos();
        }
    }
}
