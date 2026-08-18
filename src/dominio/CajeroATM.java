package dominio;

import excepciones.banco.BancoExcepciones;
import excepciones.formato.FormatoExcepciones;
import menus.*;

import java.util.Scanner;

public class CajeroATM {

    private final DispensadorDinero dispensador;
    private final Scanner scanner;
    private final Banco banco;

    public CajeroATM(Scanner scanner) {
        this.dispensador = new DispensadorDinero();
        this.banco = new Banco();
        this.scanner = scanner;
    }

    public CajeroATM(Banco banco, Scanner scanner) {
        this.dispensador = new DispensadorDinero();
        this.scanner = new Scanner(System.in);
        this.banco = banco;
    }

    public void encender() throws BancoExcepciones, FormatoExcepciones {
        new MenuATM(banco, dispensador, scanner).ejecutar();
    }

}
