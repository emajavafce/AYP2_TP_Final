package dominio;

import excepciones.banco.BancoExcepciones;
import excepciones.formato.DniFormatoIncorrectoEx;
import excepciones.formato.FormatoExcepciones;
import excepciones.menu.MenuExcepciones;
import excepciones.menu.OpcionConFormatoIncorrectoEx;
import menus.*;

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

    public CajeroATM(Banco banco) throws MenuExcepciones, BancoExcepciones, FormatoExcepciones {
        this.dispensador = new DispensadorDinero();
        this.scanner = new Scanner(System.in);
        this.banco = banco;
        ejecutar();
    }

    private void ejecutar() throws MenuExcepciones, BancoExcepciones, FormatoExcepciones {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--Menu del cajero ATM--\n1-Ingresar como cliente\n2-Ingresar como banco\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            try {
                opcion = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException ex) {
                throw new OpcionConFormatoIncorrectoEx();
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
     * @throws FormatoExcepciones
     * @throws BancoExcepciones
     * @throws MenuExcepciones
     */
    private void ingresarComoCliente() throws FormatoExcepciones, BancoExcepciones, MenuExcepciones {
        String dni = pedirDni();
        Cliente cliente = banco.buscarCliente(dni);
        System.out.println("## BIENVENIDO/A " + cliente.getNombre().toUpperCase() + " ##");
        new MenuClientes(cliente, dispensador, scanner).ejecutar();
    }

    /**
     * Se ingresa como Banco
     *
     * @throws FormatoExcepciones
     * @throws BancoExcepciones
     * @throws MenuExcepciones
     */
    private void ingresarComoBanco() throws FormatoExcepciones, BancoExcepciones, MenuExcepciones {
        new MenuBanco(banco, scanner);
    }

    /**
     * Solicita el DNI y verifica que lo ingresado tenga el formato correcto
     *
     * @return
     * @throws DniFormatoIncorrectoEx
     */
    public String pedirDni() throws DniFormatoIncorrectoEx {
        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        if (!Verificador.dniCorrecto(dni)) {
            throw new DniFormatoIncorrectoEx();
        }
        return dni;
    }
}
