import java.util.*;

import excepciones.*;

public class Banco {

    private final Set<String> aliasUsados;
    private final Map<String, Cliente> aliasCliente;
    private final Map<String, Cliente> dniCliente;
    private static final double PRECIO_DOLAR = 1500;
    private static final String[] PALABRAS = {"perro", "feo", "escobar", "pala", "marron", "manicero", "kuka", "gato", "mono", "mesa", "jijolines"};

    public Banco() {
        this.aliasUsados = new HashSet<>();
        this.aliasCliente = new HashMap<>();
        this.dniCliente = new HashMap<>();
    }

    /**
     * Agrega un cliente al registro del Banco
     *
     * @param cliente
     * @throws NullPointerException
     */
    public void agregarCliente(Cliente cliente) throws IllegalArgumentException, ClienteYaRegistradoEx, CantMaxAliasSuperadaEx {
        if (cliente == null) {
            throw new IllegalArgumentException();
        }
        String dni = cliente.getDni();
        if (this.existeCliente(dni)) {
            throw new ClienteYaRegistradoEx();
        }
        String alias = this.generarAlias();
        cliente.setAlias(alias);
        this.aliasUsados.add(alias);
        this.aliasCliente.put(alias, cliente);
        this.dniCliente.put(cliente.getDni(), cliente);
    }

    /**
     * Muestra todos los clientes registrados en el Banco
     *
     * @throws NoHayClientesCargadosEx
     */
    public void listarClientes() throws NoHayClientesCargadosEx {
        if (!this.hayClientesRegistrados()) {
            throw new NoHayClientesCargadosEx();
        }
        for (Cliente cliente : this.dniCliente.values()) {
            System.out.println(cliente);
        }
    }

    /**
     * Elimina un cliente del registro del Banco
     *
     * @param dni
     * @throws NoExisteClienteEx
     */
    public void eliminarCliente(String dni) throws NoExisteClienteEx {
        Cliente cliente = this.dniCliente.remove(dni);
        if (cliente == null)
            throw new NoExisteClienteEx();
        aliasCliente.remove(cliente.getAlias());
        aliasUsados.remove(cliente.getAlias());
    }

    /**
     * Se verifica si existe un cliente en el registro del Banco
     *
     * @param dni
     * @return
     */
    public boolean existeCliente(String dni) {
        return this.dniCliente.containsKey(dni);
    }

    /**
     * Busca un cliente dentro de los registro del Banco
     *
     * @param dni
     * @return
     * @throws NoExisteClienteEx
     */
    public Cliente buscarCliente(String dni) throws NoExisteClienteEx {
        Cliente cliente = dniCliente.get(dni);
        if (cliente == null)
            throw new NoExisteClienteEx();
        return cliente;
    }

    /**
     * Genera un alias unico e irrepetible entre los ya registrados
     *
     * @return
     */
    public String generarAlias() throws CantMaxAliasSuperadaEx {
        int cantMaxAlias = (int) Math.pow(PALABRAS.length, 3);
        if (this.aliasUsados.size() == cantMaxAlias) {
            throw new CantMaxAliasSuperadaEx();
        }
        String alias;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                int j = getNumAleatorio(Banco.PALABRAS.length);
                sb.append(Banco.PALABRAS[j]);
                if (i < 2) {
                    sb.append(".");
                }
            }
            alias = sb.toString();
        } while (!this.aliasDisponible(alias) || !aliasCorrecto(alias));
        return alias;
    }

    /**
     * Verifica si el alias generado no esta en uso
     *
     * @param alias
     * @return
     */
    private boolean aliasDisponible(String alias) {
        return !this.aliasUsados.contains(alias);
    }

    /**
     * Verifica si el alias generado tiene la cantidad de caracteres necesaria
     *
     * @param alias
     * @return
     */
    public boolean aliasCorrecto(String alias) {
        return alias.length() <= 20;
    }

    /**
     * Verifica si hay clientes registrados en el Banco
     *
     * @return
     */
    public boolean hayClientesRegistrados() {
        return !this.dniCliente.isEmpty();
    }

    /**
     * Genera un numero entero aleatorio entre el 0 y un valor maximo sin incluir
     *
     * @param max
     * @return
     */
    private int getNumAleatorio(int max) {
        return (int) (Math.random() * max);
    }

}
