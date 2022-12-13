package daw.produceCatering.helper;

import javax.validation.ValidationException;

public class ValidationHelper {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void validateDNI(String itDNI, String error) {
        String strDNI = itDNI.trim().replaceAll("", "");
        if (strDNI.length() == 9 ){
            if (isNumeric(strDNI.substring(0,8))) {
                int intPartDNI = Integer.parseInt(strDNI.substring(0,8));
                char cLetraDNI = strDNI.charAt(8);
                int valNumDni = intPartDNI % 23;
                if ("TRWAGMYFPDXBNJZSQVHLCKE".charAt(valNumDni) != cLetraDNI) {
                    throw new ValidationException("error de validación: " + error);
                }
            } else {
                throw new ValidationException("error de validación: " + error);
            }
        } else {
            throw new ValidationException("error de validación: " + error);
        }
    }
}
