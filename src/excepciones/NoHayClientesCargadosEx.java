package excepciones;

public class NoHayClientesCargadosEx extends Exception {

    public NoHayClientesCargadosEx() {
        super("[ERROR] No hay clientes cargados...");
    }
}
