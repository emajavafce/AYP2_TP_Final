package dominio;

import java.util.Scanner;

public class LectorDeTarjetas {

    public static String tomarDatos(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Ingrese su DNI: ");
        String dni = scn.nextLine();
        return dni;
    }
}
