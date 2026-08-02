package menus;

import dominio.*;
import excepciones.banco.*;
import excepciones.formato.*;

import java.util.Collection;
import java.util.Scanner;

public class MenuBanco {

    private final Banco banco;
    private final Scanner scanner;

    public MenuBanco(Banco banco, Scanner scanner) {
        this.banco = banco;
        this.scanner = scanner;
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
                    agregarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    eliminarCliente();
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
     * Se solicita el DNI al cliente
     *
     */
    private String pedirDni() throws FormatoExcepciones {
        System.out.print("Ingrese el DNI: ");
        String dni = this.scanner.nextLine();
        if (!VerificadorDatosInput.dniCorrecto(dni)) {
            throw new DniFormatoIncorrectoEx();
        }
        return dni;
    }

    /**
     * Solicita el nombre y apellido del cliente
     *
     */
    private String[] pedirNombreApellido() throws FormatoExcepciones {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        if (!VerificadorDatosInput.nombreApellidoCorrectos(nombre, apellido)) {
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
            edad = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new FormatoEdadIncorrectoEx();
        }
        if (edad < 16 || edad > 120) {
            throw new EdadIncorrectaEx();
        }
        return edad;
    }

    /**
     * Agrega un cliente al registro del dominio.Banco
     *
     */
    private void agregarCliente() throws BancoExcepciones, FormatoExcepciones {
        String dni = pedirDni();
        if (banco.existeCliente(dni)) {
            throw new ClienteYaRegistradoEx();
        }
        String[] datos = pedirNombreApellido();
        int edad = pedirEdad();
        banco.agregarCliente(new Cliente(banco, datos[0], datos[1], dni, edad));
        System.out.println("[INFO] El cliente ha sido agregado correctamente!");
    }

    /**
     * Muestra todos los clientes registrados hasta el momento
     */
    private void listarClientes() throws BancoExcepciones {
        Collection<Cliente> clientes = banco.getClientesRegistrados();
        System.out.println("[INFO] Clientes registrados:");
        for (Cliente cliente : clientes) {
            System.out.println("\t" + cliente.getDatosPersonales());
        }
    }

    /**
     * Busca un cliente dentro de los registro del Banco
     */
    private void buscarCliente() throws FormatoExcepciones, BancoExcepciones {
        String dni = pedirDni();
        Cliente cliente = banco.buscarCliente(dni);
        System.out.println("[INFO] El cliente buscado es:");
        System.out.println("\t" + cliente.getDatosPersonales());
    }

    /**
     * Elimina un cliente de los registros del dominio.Banco
     *
     */
    private void eliminarCliente() throws FormatoExcepciones, BancoExcepciones {
        String dni = pedirDni();
        banco.eliminarCliente(dni);
        System.out.println("[INFO] El cliente ha sido eliminado!");
    }

}
