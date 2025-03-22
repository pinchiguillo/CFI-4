package com.publicaciones.models;

public class Usuario {
    private String nombre;
    private String rol;
    private String username;
    private String password;

    public Usuario(String nombre, String rol, String username, String password) {
        this.nombre = nombre;
        this.rol = rol;
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Método de autenticación (ejemplo simple)
    public boolean autenticar(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Método para verificar permisos según el rol del usuario
    public boolean tienePermiso(String permiso) {
        // Si el usuario es administrador, se asume que tiene todos los permisos
        if ("admin".equalsIgnoreCase(this.rol)) {
            return true;
        }
        // Para otros roles, se podrían implementar condiciones específicas
        // Por simplicidad se retorna false en este ejemplo
        return false;
    }

    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", rol=" + rol + ", username=" + username + "]";
    }
}
