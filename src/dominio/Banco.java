package dominio;

import java.util.*;

import cuentas.Cuenta;
import excepciones.banco.*;

public class Banco {

    private final Set<String> aliasRegistrados;
    private final Map<String, Cliente> dniCliente;
    private final Map<String, Cuenta> aliasCuenta;
    public static final double PRECIO_DOLAR = 1500;
    private static final String[] PALABRAS = {"perro", "feo", "escoba", "pala", "marron", "rojo", "kuka", "gato", "mono", "mesa", "pato", "leon", "ro", "luz"};
    private static final int CANT_LIMITE_ALIAS = PALABRAS.length * (PALABRAS.length - 1) * (PALABRAS.length - 2);

    public Banco() {
        this.aliasRegistrados = new HashSet<>();
        this.dniCliente = new HashMap<>();
        this.aliasCuenta = new HashMap<>();
    }

    /**
     * Agrega un cliente al registro del banco.
     * Genera y asigna los alias de sus cuentas antes de registrarlo.
     *
     */
    public void agregarCliente(Cliente cliente) throws BancoExcepciones {
        String dni = cliente.getDni();
        if (existeCliente(dni)) {
            throw new ClienteYaRegistradoEx();
        }
        if (CANT_LIMITE_ALIAS - aliasRegistrados.size() < 3) {
            throw new CantMaxAliasSuperadaEx();
        }
        String aliasCuentaCorriente = generarAlias();
        String aliasCajaAhorroPesos = generarAlias();
        String aliasCajaAhorroDolares = generarAlias();
        cliente.getCuentaCorriente().setAlias(aliasCuentaCorriente);
        cliente.getCajaAhorroPesos().setAlias(aliasCajaAhorroPesos);
        cliente.getCajaAhorroDolares().setAlias(aliasCajaAhorroDolares);
        registrarAlias(aliasCuentaCorriente, aliasCajaAhorroPesos, aliasCajaAhorroDolares);
        dniCliente.put(dni, cliente);
        aliasCuenta.put(aliasCuentaCorriente, cliente.getCuentaCorriente());
        aliasCuenta.put(aliasCajaAhorroPesos, cliente.getCajaAhorroPesos());
        aliasCuenta.put(aliasCajaAhorroDolares, cliente.getCajaAhorroDolares());
    }

    /**
     * Elimina un cliente del registro del Banco
     */
    public void eliminarCliente(String dni) throws NoExisteClienteEx {
        Cliente cliente = dniCliente.remove(dni);
        if (cliente == null) throw new NoExisteClienteEx();
        liberarAlias(cliente.getTodosLosAlias());
    }

    /**
     * Se verifica si existe un cliente en el registro del Banco
     */
    public boolean existeCliente(String dni) {
        return this.dniCliente.containsKey(dni);
    }

    /**
     * Busca un cliente dentro de los registro del Banco
     */
    public Cliente buscarClientePorDni(String dni) throws NoExisteClienteEx {
        Cliente cliente = dniCliente.get(dni);
        if (cliente == null) throw new NoExisteClienteEx();
        return cliente;
    }

    /**
     * Busca una cuenta segun un alias
     */
    public Cuenta buscarCuentaPorAlias(String alias) throws NoExisteClienteEx {
        Cuenta cuenta = aliasCuenta.get(alias);
        if (cuenta == null) throw new NoExisteClienteEx();
        return cuenta;
    }

    /**
     * Genera un alias unico e irrepetible entre los ya registrados
     */
    public String generarAlias() throws CantMaxAliasSuperadaEx {
        if (aliasRegistrados.size() >= CANT_LIMITE_ALIAS) {
            throw new CantMaxAliasSuperadaEx();
        }
        String alias;
        do {
            StringBuilder sb = new StringBuilder();
            int primeraPalabra = getNumAleatorio(PALABRAS.length);
            int segundaPalabra;
            do {
                segundaPalabra = getNumAleatorio(PALABRAS.length);
            } while (segundaPalabra == primeraPalabra);
            int terceraPalabra;
            do {
                terceraPalabra = getNumAleatorio(PALABRAS.length);
            } while (terceraPalabra == primeraPalabra || terceraPalabra == segundaPalabra);
            sb.append(PALABRAS[primeraPalabra]).append(".").append(PALABRAS[segundaPalabra]).append(".").append(PALABRAS[terceraPalabra]);
            alias = sb.toString();
        } while (!aliasDisponible(alias) || !aliasConLongitudCorrecta(alias));
        return alias;
    }

    /**
     * Verifica si el alias generado no esta en uso
     */
    private boolean aliasDisponible(String alias) {
        return !aliasRegistrados.contains(alias);
    }

    /**
     * Verifica si el alias generado tiene la cantidad de caracteres necesaria
     */
    private boolean aliasConLongitudCorrecta(String alias) {
        return alias.length() <= 20;
    }

    /**
     * Habilita los tres alias que estaban en uso para que puedan ser usados nuevamente por otro cliente
     */
    private void liberarAlias(String... variosAlias) {
        for (String alias : variosAlias) {
            aliasRegistrados.remove(alias);
            aliasCuenta.remove(alias);
        }
    }

    /**
     * Agrega los nuevos alias generados por agregar un cliente
     */
    private void registrarAlias(String... aliasNuevos) {
        aliasRegistrados.addAll(List.of(aliasNuevos));
    }

    /**
     * Genera un numero entero aleatorio entre el 0 y un valor maximo sin incluir
     */
    private int getNumAleatorio(int max) {
        return (int) (Math.random() * max);
    }


    public Collection<Cliente> getClientesRegistrados() throws BancoExcepciones {
        if (dniCliente.isEmpty()) {
            throw new NoHayClientesCargadosEx();
        }
        return dniCliente.values();
    }

    /**
     * Se verifica si el alias esta en uso
     */
    public boolean aliasEnUso(String alias) {
        return aliasRegistrados.contains(alias);
    }
}
