
package py.edu.facitec.orionf.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class UtilidadesFecha {

    private static final DateFormat DATE_FORMAT =
            new SimpleDateFormat("dd/MM/yyyy");

    public static MaskFormatter getFormato() {
        try {
            MaskFormatter formatter = new MaskFormatter("##/##/####");

            formatter.setPlaceholderCharacter('_');
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true);

            return formatter;

        } catch (Exception e) {
            throw new RuntimeException(
                    "No se pudo configurar el formato de fecha.", e
            );
        }
    }

    public static Date stringAFecha(String fecha) {
        DATE_FORMAT.setLenient(false);

        try {
            return DATE_FORMAT.parse(fecha);
        } catch (Exception e) {
            return null;
        }
    }

    public static String fechaAString(Date fecha) {
        if (fecha == null) {
            return "";
        }

        return DATE_FORMAT.format(fecha);
    }

    public static String getFormatoFecha(Date fecha) {
        return fechaAString(fecha);
    }
}
