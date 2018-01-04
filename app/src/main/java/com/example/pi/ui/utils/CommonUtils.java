package com.example.pi.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *
 */

public class CommonUtils {

    /**
     * http://programacion.net/articulo/expresiones_regulares_en_java_127
     * Meotdo que comprueba que la contraseña tenga los siguientes requisitos
     * Debe contener al menos un digito de 0-9
     * Debe contener al menos un caracter de mayuscula
     * Debe contener al menos un caracter en minucula
     * Debe contener al menos una longitud de seis caracteres
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}";//(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,} https://regexr.com/
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
