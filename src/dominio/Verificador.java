package dominio;

public class Verificador {

    public static boolean nombreApellidoCorrectos(String nombre, String apellido) {
        String patron = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(\\s[A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$";
        return nombre.matches(patron) && apellido.matches(patron);
    }

    public static boolean dniCorrecto(String dni) {
        return dni.matches("^\\d{7,8}$");
    }

    public static boolean montoCorrecto(double monto) {
        return monto >= 0;
    }

}
