import java.util.Scanner;

public class CajeroAutomatico {

    private Scanner scn;
    private Banco banco;

    public CajeroAutomatico() {
        this.scn = new Scanner(System.in);
        this.banco = new Banco();
    }

    public void menuPrincipal() {
        int opcion = 0;
        Cliente cliente = null;
        while (opcion != 3) {
            System.out.println("--MENU DEL ATM--\n1-Ingresar siendo cliente\n2-Consultar disponibilidad\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            switch (opcion) {
                case 1:
                    break;
                case 2:

                case 3:
                    System.out.println("[INFO] Saliendo...");
                    break;
                default:
                    System.out.println("[ERROR] Opcion incorrecta");
            }
        }
    }

    private void menuCliente() {

    }

    private void ingresarComoCliente() {
        String dni = this.pedirDni();
        if(!this.dniCorrecto(dni)){
            System.out.println("[ERROR] El DNI ingresado es incorrecto");
            return;
        }
        if (!this.banco.existeCliente(dni)) {
            System.out.println("[ERROR] Cliente inexistente");
            return;
        }
        Cliente cliente = this.banco.buscarCliente(dni);
    }

    private void verificarDisponibilidad() {
        String dni = this.pedirDni();
    }

    private String pedirDni() {
        System.out.println("Ingrese el DNI: ");
        return this.scn.nextLine();
    }

    private boolean dniCorrecto(String dni){
        return dni.matches("^\\d{7,8}$");
    }
}
