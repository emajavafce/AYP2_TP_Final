package menus;

import cuentas.Cuenta;
import dominio.*;
import excepciones.banco.*;
import excepciones.formato.*;
import excepciones.menu.*;

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
        while (opcion != 6) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    retirarEfectivo();
                    System.out.println("[INFO] El dinero ha sido retirado!");
                    break;
                case 2:
                    comprarDolares();
                    System.out.println("[INFO] Los dolares han sido comprados!");
                    break;
                case 3:
                    depositarFondos();
                    System.out.println("[INFO] El dinero ha sido depositado!");
                    break;
                case 4:
                    // POR IMPLEMENTAR
                    break;
                case 5:
                    cliente.mostrarEstadoDeCuentas();
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

    /**
     * El cliente retira efecto de alguna de sus cuentas en pesos
     *
     * @throws FormatoExcepciones
     * @throws BancoExcepciones
     */
    private void retirarEfectivo() throws MenuExcepciones, FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaEnPesos = elegirCualCuentaEnPesos();
        System.out.print("Ingrese el monto a retirar: ");
        int montoSolicitado;
        try {
            montoSolicitado = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        if (!dispensador.hayStockSuficiente(montoSolicitado)) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        cliente.retirarDinero(cuentaEnPesos, montoSolicitado);
        dispensador.entregarBilletes(montoSolicitado);
    }

    /**
     * El cliente compra dolares con el dinero disponible en sus cuentas de pesos
     *
     * @throws FormatoExcepciones
     * @throws BancoExcepciones
     */
    private void comprarDolares() throws MenuExcepciones, FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaEnPesos = elegirCualCuentaEnPesos();
        Cuenta cuentaEnDolares = cliente.getCajaAhorroDolares();
        int montoDolarSolicitado;
        try {
            System.out.print("Ingrese el monto de dolares que quiere comprar: ");
            montoDolarSolicitado = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        if (montoDolarSolicitado <= 0) {
            throw new MontoIncorrectoEx();
        }
        double montoEnPesosNecesario = montoDolarSolicitado * Banco.PRECIO_DOLAR;
        if (cuentaEnPesos.getSaldo() < montoEnPesosNecesario) {
            throw new SaldoInsufParaComprarDolaresEx();
        }
        cuentaEnPesos.disminuirSaldo(montoEnPesosNecesario);
        cuentaEnDolares.aumentarSaldo(montoDolarSolicitado);
    }

    /**
     * Se deposita un monto en una cuenta determinada
     *
     * @throws MenuExcepciones
     * @throws FormatoExcepciones
     * @throws BancoExcepciones
     */
    private void depositarFondos() throws MenuExcepciones, FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaDestino = elegirCuentaDestino();
        int monto;
        try {
            System.out.print("Ingrese el monto a depositar: ");
            monto = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        cliente.depositarDinero(cuentaDestino, monto);
    }

    /**
     * Permite que el cliente seleccione la cuenta donde se depositarán los fondos. * * @return la cuenta destino seleccionada por el cliente * @throws MenuExcepciones si la opción ingresada tiene un formato inválido
     */
    private Cuenta elegirCuentaDestino() throws MenuExcepciones {
        while (true) {
            System.out.println("Seleccione el tipo de cuenta en donde se depositaran los fondos:\n1-Cuenta corriente\n2-Caja de ahorro en pesos\n3-Caja de ahorro en dolares");
            System.out.print("Ingrese su opcion: ");
            int opcion;
            try {
                opcion = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new OpcionConFormatoIncorrectoEx();
            }
            switch (opcion) {
                case 1:
                    return cliente.getCuentaCorriente();
                case 2:
                    return cliente.getCajaAhorroPesos();
                case 3:
                    return cliente.getCajaAhorroDolares();
                default:
                    System.out.println("[ERROR] Opción incorrecta. Intente nuevamente...");
            }
        }
    }

    /**
     * Permite al cliente elegir la cuenta en pesos que va utilizar
     *
     * @return
     * @throws FormatoExcepciones
     */
    private Cuenta elegirCualCuentaEnPesos() throws MenuExcepciones {
        while (true) {
            System.out.println("Seleccione el tipo de cuenta en pesos a utilizar:\n1-Cuenta corriente\n2-Caja de ahorro en pesos");
            System.out.print("Ingrese su opcion: ");
            int opcion;
            try {
                opcion = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException ex) {
                throw new OpcionConFormatoIncorrectoEx();
            }
            switch (opcion) {
                case 1:
                    return cliente.getCuentaCorriente();
                case 2:
                    return cliente.getCajaAhorroPesos();
                default:
                    System.out.println("[ERROR] La opcion ingresada es incorrecta...");
            }
        }
    }
}
