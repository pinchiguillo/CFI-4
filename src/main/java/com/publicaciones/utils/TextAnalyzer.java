package com.publicaciones.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {

    // Cuenta la cantidad de palabras en el texto
    public static int contarPalabras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0;
        }
        String[] palabras = texto.trim().split("\\s+");
        return palabras.length;
    }

    // Busca la palabra especificada en el texto y retorna una lista con las posiciones donde se encuentra
    public static List<Integer> buscarPalabra(String texto, String palabra) {
        List<Integer> indices = new ArrayList<>();
        if (texto == null || palabra == null || palabra.isEmpty()) {
            return indices;
        }
        int index = texto.indexOf(palabra);
        while (index >= 0) {
            indices.add(index);
            index = texto.indexOf(palabra, index + palabra.length());
        }
        return indices;
    }

    // Detecta patrones en el texto utilizando una expresión regular y retorna las coincidencias encontradas
    public static List<String> detectarPatrones(String texto, String regex) {
        List<String> coincidencias = new ArrayList<>();
        if (texto == null || regex == null || regex.isEmpty()) {
            return coincidencias;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            coincidencias.add(matcher.group());
        }
        return coincidencias;
    }
}
