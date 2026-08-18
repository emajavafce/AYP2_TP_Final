package menus;

import dominio.*;
import excepciones.banco.*;
import excepciones.formato.*;

import java.util.Collection;
import java.util.Scanner;

public class MenuBanco extends Menu {

    public MenuBanco(Banco banco, Scanner scanner) {
        super(banco, scanner);
    }

    /**
     * Menu interactivo del Banco
     */
    public void ejecutar() throws BancoExcepciones, FormatoExcepciones {
        int opcion = 0;
        while (opcion != 5) {
            System.out.println(" --- Menu del banco ---\n1-Agregar cliente\n2-Listar clientes\n3-Buscar cliente\n4-Eliminar cliente\n5-Salir");
            System.out.print("Ingrese su opcion: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("[ERROR] Formato incorrecto. Ingrese un número válido...");
                continue;
            }
            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 5:
                    System.out.println("[INFO] Saliendo del menu del Banco...");
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta. Intente nuevamente...");
            }
        }
    }

    /**
     * Solicita el nombre y apellido del cliente
     */
    private String[] pedirNombreApellido() throws FormatoExcepciones {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        if (!UtilidadesInput.nombreApellidoCorrectos(nombre, apellido)) {
            throw new FormatoNombreApellidoIncorrectoEx();
        }
        return new String[]{nombre, apellido};
    }

    /**
     * Se solicita la edad del cliente. Debe tener mas de 15 años y menos de 121
     */
    private int pedirEdad() throws FormatoExcepciones, BancoExcepciones {
        System.out.print("Ingrese la edad: ");
        int edad;
        try {
            edad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoEdadIncorrectoEx();
        }
        if (edad < 16 || edad > 120) {
            throw new EdadIncorrectaEx();
        }
        return edad;
    }

    /**
     * Agrega un cliente al registro del Banco
     */
    private void agregar() throws BancoExcepciones, FormatoExcepciones {
        String dni = UtilidadesInput.pedirDni(scanner);
        String[] datos = pedirNombreApellido();
        int edad = pedirEdad();
        banco.agregarCliente(new Cliente(banco, datos[0], datos[1], dni, edad));
        System.out.println("[INFO] El cliente ha sido agregado correctamente!");
    }

    /**
     * Muestra todos los clientes registrados hasta el momento
     */
    private void listar() throws BancoExcepciones {
        Collection<Cliente> clientes = banco.getClientesRegistrados();
        System.out.println("[INFO] Clientes registrados:");
        for (Cliente cliente : clientes) {
            System.out.println("\t" + cliente.datosPersonales());
        }
    }

    /**
     * Busca un cliente dentro de los registro del Banco
     */
    private void buscar() throws FormatoExcepciones, BancoExcepciones {
        String dni = UtilidadesInput.pedirDni(scanner);
        Cliente cliente = banco.buscarClientePorDni(dni);
        System.out.println("[INFO] El cliente buscado es:");
        System.out.println("\t" + cliente.datosPersonales());
    }

    /**
     * Elimina un cliente de los registros del Banco
     */
    private void eliminar() throws FormatoExcepciones, BancoExcepciones {
        String dni = UtilidadesInput.pedirDni(scanner);
        banco.eliminarCliente(dni);
        System.out.println("[INFO] El cliente ha sido eliminado!");
    }

}
