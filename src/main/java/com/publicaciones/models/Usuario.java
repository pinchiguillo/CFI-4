package com.publicaciones.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "rol", nullable = false, length = 50)
    private String rol;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publicaciones = new ArrayList<>();

    public Usuario() {
        // Constructor vacío requerido por Hibernate
    }

    public Usuario(String nombre, String rol, String username, String password) {
        this.nombre = nombre;
        this.rol = rol;
        this.username = username;
        this.password = password;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    // Métodos lógicos

    public boolean autenticar(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean tienePermiso(String permiso) {
        if ("admin".equalsIgnoreCase(this.rol)) {
            return true;
        }
        return false;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        publicacion.setAutor(this);
    }

    public void eliminarPublicacion(Publicacion publicacion) {
        publicaciones.remove(publicacion);
        publicacion.setAutor(null);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
