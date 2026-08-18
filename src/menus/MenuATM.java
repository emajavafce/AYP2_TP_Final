package menus;

import dominio.Banco;
import dominio.Cliente;
import dominio.DispensadorDinero;
import dominio.UtilidadesInput;
import excepciones.banco.BancoExcepciones;
import excepciones.formato.FormatoExcepciones;

import java.util.Scanner;

public class MenuATM extends Menu{


    private final DispensadorDinero dispensador;

    public MenuATM(Banco banco, DispensadorDinero dispensador, Scanner scanner) {
        super(banco, scanner);
        this.dispensador = dispensador;
    }

    public void ejecutar() throws BancoExcepciones, FormatoExcepciones {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--Menu del cajero ATM--\n1-Ingresar como cliente\n2-Ingresar como banco\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("[ERROR] Formato incorrecto. Ingrese un número válido...");
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
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
            }
        }
        scanner.close();
    }

    /**
     * Si se ingresa como cliente, se solicita el DNI para verificar si esta registrado o no
     */
    private void ingresarComoCliente() throws FormatoExcepciones, BancoExcepciones {
        String dni = UtilidadesInput.pedirDni(scanner);
        Cliente cliente = banco.buscarClientePorDni(dni);
        System.out.println("## BIENVENIDO/A " + cliente.getNombre().toUpperCase() + " ##");
        new MenuClientes(banco, cliente, dispensador, scanner).ejecutar();
    }

    /**
     * Se ingresa como Banco
     */
    private void ingresarComoBanco() throws BancoExcepciones, FormatoExcepciones {
        new MenuBanco(banco, scanner).ejecutar();
    }
}
