package com.lab.xss_sqli_lab.util;


public class InputSanitizer {

    public static String sanitizeString(String input) {
        if (input == null) return null;
        return input
                .trim()
                .replaceAll("\\s+", " ")
                .replace("\u200B", "")
                .strip();
    }

    public static String sanitizeCpf(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("\\D", "");
    }

    public static String sanitizeCelular(String celular) {
        if (celular == null) return null;
        return celular.replaceAll("\\D", "");
    }

    public static String sanitizeEmail(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }

    public static String sanitizePais(String pais) {
        return sanitizeString(pais);
    }
}

