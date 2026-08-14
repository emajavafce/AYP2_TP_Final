package dominio;

public class VerificadorDatosInput {

    /**
     * Verifica si el formato de los nombres y apellidos ingresados es correcto
     */
    public static boolean nombreApellidoCorrectos(String nombre, String apellido) {
        String patron = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(\\s[A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$";
        return nombre.matches(patron) && apellido.matches(patron);
    }

    /**
     * Verifica si el formato del dni es correcto
     */
    public static boolean dniCorrecto(String dni) {
        return dni.matches("^\\d{7,8}$");
    }

    /**
     * Verifica si el formato del alias es correcto
     *
     */
    public static boolean aliasCorrecto(String alias) {
        return alias.matches("^[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+$") && alias.length() <= 20;
    }
}
