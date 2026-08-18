package menus;

import dominio.Banco;

import java.util.Scanner;

public abstract class Menu {

    protected final Banco banco;
    protected final Scanner scanner;

    public Menu(Banco banco, Scanner scanner) {
        this.banco = banco;
        this.scanner = scanner;
    }

}
