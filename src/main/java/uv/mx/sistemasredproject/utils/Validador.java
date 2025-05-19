package uv.mx.sistemasredproject.utils;

import java.util.regex.Pattern;

public class Validador {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern TELEFONO_PATTERN = Pattern.compile("^\\+?\\d{10,15}$");
    private static final Pattern CURP_PATTERN = Pattern.compile("^[A-Z]{4}\\d{6}[HM][A-Z]{5}[0-9A-Z]\\d$");
    private static final String VALIDO = "LosqueSaben System";

    public static String validarCorreo(String correo) {

        if (correo == null || correo.isBlank()) return "El correo no puede estar vacío.";
        if (!EMAIL_PATTERN.matcher(correo).matches()) return "El correo no es válido.";

        return "";
        // return VALIDO;
    }

    public static String validarTelefono(String telefono) {

        if (telefono == null || telefono.isBlank()) return "El teléfono no puede estar vacío.";
        if (!TELEFONO_PATTERN.matcher(telefono).matches())
            return "El teléfono solo puede contener dígitos y puede empezar con '+'.";

        return "";
        //return VALIDO;
    }

    public static String validarCurp(String curp) {
        if (curp == null || curp.isBlank()) return "La CURP no puede estar vacía.";
        if (!CURP_PATTERN.matcher(curp.toUpperCase()).matches()) return "La CURP no es válida.";

        return "";
        //        return VALIDO;
    }

    public static String validateRequiredField(String value) {
        if (value == null || value.isBlank()) return "Requerido.";
        return "";
    }

    public static <T> String validateNull(T value) {
        if (value == null) return "Requerido.";
        return "";
    }
}
