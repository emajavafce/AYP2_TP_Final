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

    public void ejecutar() {
        int opcion = 0;
        String menu = " --- Menu del banco ---\n1-Agregar cliente\n2-Listar clientes\n3-Buscar cliente\n4-Eliminar cliente\n5-Salir";
        while (opcion != 5) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scanner.nextLine());
            switch (opcion) {
                case 1:
                    this.agregarCliente();
                    break;
                case 2:
                    this.listarClientes();
                    break;
                case 3:
                    this.buscarCliente();
                    break;
                case 4:
                    this.eliminarCliente();
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

    private void agregarCliente() {
        String dni = this.pedirDni();
        if (!Verificador.dniCorrecto(dni)) {
            System.out.println("[ERROR] El DNI ingresado es incorrecto");
            return;
        }
        if (this.banco.existeCliente(dni)) {
            System.out.println("[ERROR] Ya existe un cliente registrado con ese DNI");
            return;
        }
        System.out.print("Ingrese el nombre: ");
        String nombre = this.scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = this.scanner.nextLine();
        System.out.print("Ingrese la edad: ");
        int edad;
        try {
            edad = Integer.parseInt(this.scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("[ERROR] La edad debe ser un numero");
            return;
        }
        boolean datosCorrectos = Verificador.nombreApellidoCorrectos(nombre, apellido) && Verificador.edadCorrecta(edad);
        if (datosCorrectos) {
            Cliente cliente = new Cliente(nombre, apellido, dni, edad);
            this.banco.agregarCliente(cliente);
            System.out.println("[INFO] El cliente ha sido agregado");
        } else {
            System.out.println("[ERROR] Uno o varios de los datos ingresados son incorrectos");
        }
    }

    private void listarClientes() {
        if (!this.banco.hayClientesRegistrados()) {
            System.out.println("[INFO] No hay clientes registrados por el momento");
            return;
        }
        System.out.println("Clientes registrados:");
        this.banco.listarClientes();
    }

    private void buscarCliente() {
        String dni = this.pedirDni();
        if (!Verificador.dniCorrecto(dni)) {
            System.out.println("[ERROR] El DNI ingresado es incorrecto");
            return;
        }
        if (!this.banco.existeCliente(dni)) {
            System.out.println("[INFO] No existe cliente con ese DNI");
            return;
        }
        Cliente cliente = this.banco.buscarCliente(dni);
        System.out.println(cliente);
    }

    private void eliminarCliente() {
        String dni = this.pedirDni();
        if (!this.banco.existeCliente(dni)) {
            System.out.println("[ERROR] No existe cliente con ese DNI");
            return;
        }
        this.banco.eliminarCliente(dni);
    }

    private boolean edadCorrecta(int edad) {
        return edad >= 16 && edad <= 120;
    }

    private String pedirDni() {
        System.out.print("Ingrese el DNI: ");
        return this.scanner.nextLine();
    }
}
