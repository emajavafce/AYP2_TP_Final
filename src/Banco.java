import java.util.*;

public class Banco {

    private Set<String> aliasUsados;
    private Map<String, Cliente> aliasCliente;
    private Map<String, Cliente> dniCliente;

    public Banco() {
        this.aliasUsados = new HashSet<>();
        this.aliasCliente = new HashMap<>();
        this.dniCliente = new HashMap<>();
    }

    public void agregarCliente(Cliente cliente) {
        String alias = this.generarAlias();
        this.aliasCliente.put(alias, cliente);
        this.dniCliente.put(cliente.getDni(), cliente);
    }

    public void listarClientes() {
        for (String alias : this.clientes.keySet()) {
            System.out.println("\t"+ this.clientes.get(alias).toString());
        }
    }

    public void eliminarCliente(String alias) {
        if (this.clientes.remove(alias) == null) {
            System.out.println("[ERROR] No existe el cliente que se quiere eliminar");
        } else{
            this.aliasUsados.remove(alias);
        }
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

    private int getNumAleatorio(int max) {
        return (int) (Math.random() * max);
    }
}
