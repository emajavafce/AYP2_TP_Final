import java.util.*;

public class Banco {

    private final Set<String> aliasUsados;
    private final Map<String, Cliente> aliasCliente;
    private final Map<String, Cliente> dniCliente;

    public Banco() {
        this.aliasUsados = new HashSet<>();
        this.aliasCliente = new HashMap<>();
        this.dniCliente = new HashMap<>();
    }

    public void agregarCliente(Cliente cliente) {
        String alias = this.generarAlias();
        this.aliasUsados.add(alias);
        this.aliasCliente.put(alias, cliente);
        this.dniCliente.put(cliente.getDni(), cliente);
    }

    public void listarClientes() {
        for (String dni : this.dniCliente.keySet()) {
            System.out.println(this.dniCliente.get(dni));
        }
    }

    public void eliminarCliente(String dni) {
        Cliente cliente = this.dniCliente.get(dni);
        this.dniCliente.remove(dni);
        this.aliasUsados.remove(cliente.getAlias());
        this.aliasCliente.remove(cliente.getAlias());
        System.out.println("[INFO] Cliente eliminado correctamente");
    }

    public boolean existeCliente(String dni) {
        return this.dniCliente.containsKey(dni);
    }

    public Cliente buscarCliente(String dni) {
        return this.dniCliente.get(dni);
    }

    public String generarAlias() {
        String[] palabras = {"perro", "feo", "escobar", "pala", "marron", "manicero", "kuka", "gato", "mono", "mesa", "jijolines"};
        String alias;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                int j = getNumAleatorio(palabras.length);
                sb.append(palabras[j]);
                if (i < 2) {
                    sb.append(".");
                }
            }
            alias = sb.toString();
        } while (!aliasCorrecto(alias));
        aliasUsados.add(alias);
        return alias;
    }

    public boolean aliasCorrecto(String alias) {
        return !this.aliasUsados.contains(alias) && alias.length() <= 20;
    }

    public boolean hayClientesRegistrados() {
        return !this.dniCliente.isEmpty();
    }

    private int getNumAleatorio(int max) {
        return (int) (Math.random() * max);
    }

}
