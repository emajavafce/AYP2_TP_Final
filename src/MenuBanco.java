import excepciones.*;

import java.util.Scanner;

public class MenuBanco {

    private final Banco banco;
    private final DispensadorDinero dispensador;
    private final Scanner scanner;

    public MenuBanco(Banco banco, DispensadorDinero dispensador, Scanner scanner) {
        this.banco = banco;
        this.dispensador = dispensador;
        this.scanner = scanner;
    }

    public void ejecutar() throws Exception {
        int opcion = 0;
        String menu = " --- Menu del banco ---\n1-Agregar cliente\n2-Listar clientes\n3-Buscar cliente\n4-Eliminar cliente\n5-Salir";
        while (opcion != 5) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    this.agregarCliente();
                    System.out.println("[INFO] El cliente ha sido agregado correctamente!");
                    break;
                case 2:
                    System.out.println("Clientes registrados:");
                    this.listarClientes();
                    break;
                case 3:
                    Cliente cliente = this.buscarCliente();
                    System.out.println(cliente);
                    break;
                case 4:
                    this.eliminarCliente();
                    System.out.println("[INFO] El cliente ha sido eliminado!");
                    break;
                case 5:
                    this.scanner.close();
                    System.out.println("[INFO] Saliendo del programa");
                    break;
                default:
                    System.out.println("[ERROR] La opcion ingresada es incorrecta");
            }
        }
    }

    /**
     * Se solicita el DNI al cliente
     *
     * @return
     * @throws DniFormatoIncorrectoEx
     */
    private String pedirDni() throws DniFormatoIncorrectoEx {
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
     * @throws FormatoNombreApellidoIncorrectoEx Si el nombre o apellido tiene un formato erroneo
     */
    private String[] pedirNombreApellido() throws FormatoNombreApellidoIncorrectoEx {
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
    private int pedirEdad() throws FormatoEdadIncorrectoEx, EdadIncorrectaEx {
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
     * Agrega un cliente al registro del Banco
     *
     * @throws DniFormatoIncorrectoEx
     * @throws ClienteYaRegistradoEx
     * @throws CantMaxAliasSuperadaEx
     * @throws FormatoEdadIncorrectoEx
     * @throws EdadIncorrectaEx
     * @throws FormatoNombreApellidoIncorrectoEx
     */
    private void agregarCliente() throws DniFormatoIncorrectoEx, ClienteYaRegistradoEx, CantMaxAliasSuperadaEx, FormatoEdadIncorrectoEx, EdadIncorrectaEx, FormatoNombreApellidoIncorrectoEx {
        String dni = this.pedirDni();
        if (this.banco.existeCliente(dni)) {
            throw new ClienteYaRegistradoEx();
        }
        String[] datos = this.pedirNombreApellido();
        String nombre = datos[0];
        String apellido = datos[1];
        int edad = this.pedirEdad();
        this.banco.agregarCliente(new Cliente(nombre, apellido, dni, edad));
    }

    /**
     * Muestra todos los clientes registrados hasta el momento
     *
     * @throws NoHayClientesCargadosEx
     */
    private void listarClientes() throws NoHayClientesCargadosEx {
        this.banco.listarClientes();
    }

    /**
     * Busca un cliente dentro de los registro del Banco
     *
     * @return
     * @throws DniFormatoIncorrectoEx
     * @throws NoExisteClienteEx
     */
    private Cliente buscarCliente() throws DniFormatoIncorrectoEx, NoExisteClienteEx {
        String dni = this.pedirDni();
        return this.banco.buscarCliente(dni);
    }

    /**
     * Elimina un cliente de los registros del Banco
     *
     * @throws DniFormatoIncorrectoEx
     * @throws NoExisteClienteEx
     */
    private void eliminarCliente() throws DniFormatoIncorrectoEx, NoExisteClienteEx {
        String dni = this.pedirDni();
        this.banco.eliminarCliente(dni);
    }


}
