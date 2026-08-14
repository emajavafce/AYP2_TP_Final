package dominio;

import excepciones.banco.BancoExcepciones;
import excepciones.formato.DniFormatoIncorrectoEx;
import excepciones.formato.FormatoExcepciones;
import menus.*;

import java.util.Scanner;

public class CajeroATM {

    private final DispensadorDinero dispensador;
    private final Scanner scanner;
    private final Banco banco;

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

    public void ejecutar() throws BancoExcepciones, FormatoExcepciones {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--Menu del cajero ATM--\n1-Ingresar como cliente\n2-Ingresar como banco\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            try {
                opcion = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("[Error] Formato incorrecto. Ingrese un número válido...");
                continue;
            }
            switch (opcion) {
                case 1:
                    ingresarComoCliente();
                    break;
                case 2:
                    ingresarComoBanco();
                    break;
                case 3:
                    System.out.println("[INFO] Saliendo...");
                    scanner.close();
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
                    break;
            }
        }
    }

    /**
     * Si se ingresa como cliente, se solicita el DNI para verificar si esta registrado o no
     *
     */
    private void ingresarComoCliente() throws FormatoExcepciones, BancoExcepciones {
        String dni = pedirDni();
        Cliente cliente = banco.buscarClientePorDni(dni);
        System.out.println("## BIENVENIDO/A " + cliente.getNombre().toUpperCase() + " ##");
        new MenuClientes(banco, cliente, dispensador, scanner).ejecutar();
    }

    /**
     * Se ingresa como Banco
     *
     */
    private void ingresarComoBanco() throws BancoExcepciones, FormatoExcepciones {
        new MenuBanco(banco, scanner).ejecutar();
    }

    /**
     * Solicita el DNI y verifica que lo ingresado tenga el formato correcto
     *
     */
    public String pedirDni() throws DniFormatoIncorrectoEx {
        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        if (!VerificadorDatosInput.dniCorrecto(dni)) {
            throw new DniFormatoIncorrectoEx();
        }
        return dni;
    }
}
