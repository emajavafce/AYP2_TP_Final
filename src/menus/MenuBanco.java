package menus;

import dominio.*;
import excepciones.banco.*;
import excepciones.formato.*;
import excepciones.menu.*;

import java.util.Scanner;

public class MenuBanco {

    private final Banco banco;
    private final Scanner scanner;

    public MenuBanco(Banco banco, Scanner scanner) {
        this.banco = banco;
        this.scanner = scanner;
    }

    public void ejecutar() throws BancoExcepciones, FormatoExcepciones, MenuExcepciones {
        int opcion = 0;
        String menu = " --- Menu del banco ---\n1-Agregar cliente\n2-Listar clientes\n3-Buscar cliente\n4-Eliminar cliente\n5-Salir";
        while (opcion != 5) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            try {
                opcion = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException ex) {
                throw new OpcionConFormatoIncorrectoEx();
            }
            switch (opcion) {
                case 1:
                    this.agregarCliente();
                    System.out.println("[INFO] El cliente ha sido agregado correctamente!");
                    break;
                case 2:
                    System.out.println("[INFO] Clientes registrados:");
                    this.listarClientes();
                    break;
                case 3:
                    Cliente cliente = this.buscarCliente();
                    System.out.println("[INFO] El cliente encontrado:");
                    System.out.println("\t" + cliente.mostrarDatosPersonales());
                    break;
                case 4:
                    this.eliminarCliente();
                    System.out.println("[INFO] El cliente ha sido eliminado!");
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
     * @return
     * @throws DniFormatoIncorrectoEx
     */
    private String pedirDni() throws FormatoExcepciones {
        System.out.print("Ingrese el DNI: ");
        String dni = this.scanner.nextLine();
        if (!Verificador.dniCorrecto(dni)) {
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
        String nombre = this.scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = this.scanner.nextLine();
        if (!Verificador.nombreApellidoCorrectos(nombre, apellido)) {
            throw new FormatoNombreApellidoIncorrectoEx();
        }
        String[] datos = {nombre, apellido};
        return datos;
    }

    /**
     * Se solicita la edad del cliente. Debe tener mas de 15 años y menos de 121
     *
     * @throws FormatoEdadIncorrectoEx
     * @throws EdadIncorrectaEx
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
        String dni = this.pedirDni();
        if (this.banco.existeCliente(dni)) {
            throw new ClienteYaRegistradoEx();
        }
        String[] datos = this.pedirNombreApellido();
        int edad = this.pedirEdad();
        this.banco.agregarCliente(new Cliente(this.banco, datos[0], datos[1], dni, edad));
    }

    /**
     * Muestra todos los clientes registrados hasta el momento
     */
    private void listarClientes() throws BancoExcepciones {
        this.banco.listarClientes();
    }

    /**
     * Busca un cliente dentro de los registro del dominio.Banco
     *
     * @return
     * @throws DniFormatoIncorrectoEx
     * @throws NoExisteClienteEx
     */
    private Cliente buscarCliente() throws FormatoExcepciones, BancoExcepciones {
        String dni = this.pedirDni();
        return this.banco.buscarCliente(dni);
    }

    /**
     * Elimina un cliente de los registros del dominio.Banco
     *
     * @throws DniFormatoIncorrectoEx
     * @throws NoExisteClienteEx
     */
    private void eliminarCliente() throws FormatoExcepciones, BancoExcepciones {
        String dni = this.pedirDni();
        this.banco.eliminarCliente(dni);
    }

}
