package com.publicaciones.models;

public class Contacto {
    private String nombre;
    private String email;
    private String telefono;

    public Contacto(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método para validar la sintaxis del email
    public boolean validarEmail() {
        // Implementación simplificada: se verifica que el email contenga el carácter "@"
        return email != null && email.contains("@");
    }

    // Método para actualizar la información del contacto
    public void actualizarContacto(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Contacto [nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + "]";
    }
}
