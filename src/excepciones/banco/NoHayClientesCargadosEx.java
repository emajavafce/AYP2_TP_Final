package excepciones.banco;

public class NoHayClientesCargadosEx extends BancoExcepciones {

    public NoHayClientesCargadosEx() {
        super("[ERROR] No hay clientes cargados...");
    }
}
