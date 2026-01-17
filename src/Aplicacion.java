import java.util.Scanner;

public class Aplicacion {

    private Scanner scn;
    private Banco banco;

    public Aplicacion() {
        this.scn = new Scanner(System.in);
        this.banco = new Banco();
    }

    public void run() {
        int opcion = 0;
        String menu = " --- Menu del banco ---\n1-Agregar cliente\n2-Listar clientes\n3-Eliminar cliente\n4-Salir";
        while (opcion != 4) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            switch (opcion) {
                case 1:
                    this.agregarCliente();
                    break;
                case 2:
                    this.listarClientes();
                    break;
                case 3:
                    this.eliminarCliente();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("[ERROR] La opcion ingresada es incorrecta");
            }
        }
    }

    private void eliminarCliente() {
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        if(this.existeCliente(dni)){

        }
        String dni = this.scn.nextLine();
    }

    private void listarClientes() {
        System.out.println("Clientes registrados:");
        this.banco.listarClientes();
    }

    private void agregarCliente() {
        System.out.print("Ingrese el nombre: ");
        String nombre = this.scn.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = this.scn.nextLine();
        System.out.print("Ingrese el dni: ");
        String dni = this.scn.nextLine();
        System.out.print("Ingrese la edad: ");
        int edad = Integer.parseInt(this.scn.nextLine());
        this.banco.agregarCliente(new Cliente(nombre, apellido, dni, edad));
        System.out.println("[INFO] El cliente ha sido agregado!");
    }
}
