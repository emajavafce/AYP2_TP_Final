package menus;

import cuentas.Cuenta;
import cuentas.Transferencia;
import dominio.*;
import excepciones.banco.*;
import excepciones.formato.*;

import java.util.Scanner;

public class MenuClientes {

    private final Banco banco;
    private final Cliente cliente;
    private final DispensadorDinero dispensador;
    private final Scanner scanner;

    public MenuClientes(Banco banco, Cliente cliente, DispensadorDinero dispensador, Scanner scanner) {
        this.banco = banco;
        this.cliente = cliente;
        this.dispensador = dispensador;
        this.scanner = scanner;
    }

    public void ejecutar() throws FormatoExcepciones, BancoExcepciones {
        int opcion = 0;
        String menu = "--- Menu de clientes ---\n1-Retirar efectivo\n2-Comprar dolares\n3-Depositar fondos\n4-Hacer transferencia\n5-Revisar estado de cuentas\n6-Salir";
        while (opcion != 6) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
                continue;
            }
            switch (opcion) {
                case 1:
                    retirarEfectivo();
                    break;
                case 2:
                    comprarDolares();
                    break;
                case 3:
                    depositarFondos();
                    break;
                case 4:
                    transferir();
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

    private void transferir() throws FormatoExcepciones, BancoExcepciones {
        System.out.print("Ingrese el alias de la cuenta a la que va transferir: ");
        String aliasDestino = scanner.nextLine();
        if (VerificadorDatosInput.aliasCorrecto(aliasDestino)) {
            throw new AliasIncorrectoEx();
        }
        if (!banco.aliasEnUso(aliasDestino)) {
            throw new NoExisteClienteEx();
        }
        Cuenta cuentaDestino = banco.buscarCuentaPorAlias(aliasDestino);
        Cuenta cuentaOrigen = elegirCualCuentaEnPesos();
        double monto = solicitarMonto();
        System.out.print("Ingrese el motivo/causa de la transferencia: ");
        String motivo = scanner.nextLine();
        Transferencia transferencia = new Transferencia(cuentaOrigen, cuentaDestino, monto, motivo);
        cliente.transferir(cuentaOrigen, cuentaDestino, monto, motivo);

        cuentaOrigen.guardarTrasferenciaRecibida(transferencia);
        cuentaDestino.guardarTrasferenciaRecibida(transferencia);
    }

    /**
     * El cliente retira efecto de alguna de sus cuentas en pesos
     */
    private void retirarEfectivo() throws FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaEnPesos = elegirCualCuentaEnPesos();
        System.out.print("Ingrese el monto a retirar: ");
        int montoSolicitado;
        try {
            montoSolicitado = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        if (!dispensador.hayStockSuficiente(montoSolicitado)) {
            throw new MontoSuperiorAlDisponibleEx();
        }
        cliente.retirarDinero(cuentaEnPesos, montoSolicitado);
        dispensador.entregarBilletes(montoSolicitado);
        System.out.println("[INFO] El dinero ha sido retirado!");
    }

    /**
     * El cliente compra dolares con el dinero disponible en sus cuentas de pesos
     */
    private void comprarDolares() throws FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaEnPesos = elegirCualCuentaEnPesos();
        Cuenta cuentaEnDolares = cliente.getCajaAhorroDolares();
        int montoDolarSolicitado;
        try {
            System.out.print("Ingrese el monto de dolares que quiere comprar: ");
            montoDolarSolicitado = Integer.parseInt(scanner.nextLine());
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
        System.out.println("[INFO] Los dolares han sido comprados!");
    }

    /**
     * Se deposita un monto en una cuenta determinada
     *
     */
    private void depositarFondos() throws FormatoExcepciones, BancoExcepciones {
        Cuenta cuentaDestino = elegirCuentaDestino();
        int monto;
        try {
            System.out.print("Ingrese el monto a depositar: ");
            monto = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoMontoIncorrectoEx();
        }
        cliente.depositarDinero(cuentaDestino, monto);
        System.out.println("[INFO] El dinero ha sido depositado!");
    }

    /**
     * Permite que el cliente seleccione la cuenta donde se depositarán los fondos. * * @return la cuenta destino seleccionada por el cliente * @throws MenuExcepciones si la opción ingresada tiene un formato inválido
     */
    private Cuenta elegirCuentaDestino() {
        while (true) {
            System.out.println("Seleccione el tipo de cuenta en donde se depositaran los fondos:\n1-Cuenta corriente\n2-Caja de ahorro en pesos\n3-Caja de ahorro en dolares");
            System.out.print("Ingrese su opcion: ");
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
                continue;
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
     */
    private Cuenta elegirCualCuentaEnPesos() {
        while (true) {
            System.out.println("Seleccione el tipo de cuenta en pesos a utilizar:\n1-Cuenta corriente\n2-Caja de ahorro en pesos");
            System.out.print("Ingrese su opcion: ");
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
                continue;
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

    private double solicitarMonto() throws FormatoExcepciones, BancoExcepciones {
        System.out.print("Ingrese el monto: ");
        double monto;
        try {
            monto = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new FormatoMontoIncorrectoEx();
        }
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        }
        return monto;
    }
}
