package dominio;

import java.util.*;

import excepciones.banco.CantMaxAliasSuperadaEx;
import excepciones.banco.ClienteYaRegistradoEx;
import excepciones.banco.NoExisteClienteEx;
import excepciones.banco.NoHayClientesCargadosEx;

public class Banco {

    private final Set<String> aliasUsados;
    private final Map<String, Cliente> dniCliente;
    public static final double PRECIO_DOLAR = 1500;
    private static final String[] PALABRAS = {"perro", "feo", "escobar", "pala", "marron", "manicero", "kuka", "gato", "mono", "mesa", "jijolines"};

    public Banco() {
        this.aliasUsados = new HashSet<>();
        this.dniCliente = new HashMap<>();
    }

    /**
     * Agrega un cliente al registro del banco.
     * Genera y asigna los alias de sus cuentas antes de registrarlo.
     *
     * @param cliente Cliente que se desea agregar.
     * @throws IllegalArgumentException si el cliente es {@code null}.
     * @throws ClienteYaRegistradoEx    si ya existe un cliente con el mismo DNI.
     * @throws CantMaxAliasSuperadaEx   si no es posible generar nuevos alias.
     */
    public void agregarCliente(Cliente cliente) throws IllegalArgumentException, ClienteYaRegistradoEx, CantMaxAliasSuperadaEx {
        if (cliente == null) {
            throw new IllegalArgumentException();
        }
        String dni = cliente.getDni();
        if (this.existeCliente(dni)) {
            throw new ClienteYaRegistradoEx();
        }
        String aliasCuentaCorriente = this.generarAlias();
        String aliasCajaAhorroPesos = this.generarAlias();
        String aliasCajaAhorroDolares = this.generarAlias();
        cliente.getCuentaCorriente().setAlias(aliasCuentaCorriente);
        cliente.getCajaAhorroPesos().setAlias(aliasCajaAhorroPesos);
        cliente.getCajaAhorroDolares().setAlias(aliasCajaAhorroDolares);
        this.agregarVariosAlias(aliasCuentaCorriente, aliasCajaAhorroPesos, aliasCajaAhorroDolares);
        this.dniCliente.put(dni, cliente);
    }

    /**
     * Muestra todos los clientes registrados en el dominio.Banco
     *
     * @throws NoHayClientesCargadosEx
     */
    public void listarClientes() throws NoHayClientesCargadosEx {
        if (!this.hayClientesRegistrados()) {
            throw new NoHayClientesCargadosEx();
        }
        for (Cliente cliente : this.dniCliente.values()) {
            System.out.println("\t" + cliente.mostrarDatosPersonales());
        }
    }

    /**
     * Elimina un cliente del registro del dominio.Banco
     *
     * @param dni
     * @throws NoExisteClienteEx
     */
    public void eliminarCliente(String dni) throws NoExisteClienteEx {
        Cliente cliente = this.dniCliente.remove(dni);
        if (cliente == null)
            throw new NoExisteClienteEx();
        this.quitarAliasUsados(cliente.getAliasDeLasCuentas());
    }

    /**
     * Se verifica si existe un cliente en el registro del dominio.Banco
     *
     * @param dni
     * @return
     */
    public boolean existeCliente(String dni) {
        return this.dniCliente.containsKey(dni);
    }

    /**
     * Busca un cliente dentro de los registro del dominio.Banco
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
     * Verifica si hay clientes registrados en el dominio.Banco
     *
     * @return
     */
    public boolean hayClientesRegistrados() {
        return !this.dniCliente.isEmpty();
    }

    /**
     * Habilita los tres alias utilizados para que puedan ser usados nuevamente por otro cliente
     *
     * @param variosAlias
     */
    private void quitarAliasUsados(String... variosAlias) {
        for (String alias : variosAlias) {
            this.aliasUsados.remove(alias);
        }
    }

    /**
     * Agrega los nuevos alias generados por agregar un cliente
     *
     * @param aliasNuevos
     */
    private void agregarVariosAlias(String... aliasNuevos) {
        for (String alias : aliasNuevos) {
            this.aliasUsados.add(alias);
        }
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
