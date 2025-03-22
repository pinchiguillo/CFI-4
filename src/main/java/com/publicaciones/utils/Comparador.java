package com.publicaciones.utils;

import java.util.ArrayList;
import java.util.List;

public class Comparador {

    // Compara dos cadenas de texto y retorna una lista con las diferencias por línea
    public static List<String> comparar(String texto1, String texto2) {
        List<String> diferencias = new ArrayList<>();
        String[] lineas1 = texto1.split("\n");
        String[] lineas2 = texto2.split("\n");
        int maxLineas = Math.max(lineas1.length, lineas2.length);

        for (int i = 0; i < maxLineas; i++) {
            String linea1 = i < lineas1.length ? lineas1[i] : "";
            String linea2 = i < lineas2.length ? lineas2[i] : "";
            if (!linea1.equals(linea2)) {
                diferencias.add("Diferencia en la línea " + (i + 1) + ":");
                diferencias.add("Archivo 1: " + linea1);
                diferencias.add("Archivo 2: " + linea2);
            }
        }
        return diferencias;
    }
}
