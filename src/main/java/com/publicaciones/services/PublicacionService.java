package com.publicaciones.services;

import com.publicaciones.models.Publicacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PublicacionService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PublicacionService() {
        emf = Persistence.createEntityManagerFactory("miPU");
        em = emf.createEntityManager();
    }

    // Agrega o actualiza una publicación en la base de datos.
    public boolean agregarPublicacion(Publicacion publicacion) {
        if (publicacion == null) {
            return false;
        }
        try {
            em.getTransaction().begin();
            if (publicacion.getId() == null) {
                em.persist(publicacion);
            } else {
                em.merge(publicacion);
            }
            em.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    // Actualiza una publicación existente identificada por su título.
    public boolean actualizarPublicacion(String titulo, Publicacion publicacionActualizada) {
        try {
            em.getTransaction().begin();
            Publicacion publicacionExistente = em.createQuery(
                            "SELECT p FROM Publicacion p WHERE p.titulo = :titulo", Publicacion.class)
                    .setParameter("titulo", titulo)
                    .getSingleResult();
            if (publicacionExistente != null) {
                publicacionExistente.setTitulo(publicacionActualizada.getTitulo());
                publicacionExistente.setContenido(publicacionActualizada.getContenido());
                publicacionExistente.setFecha(publicacionActualizada.getFecha());
                em.merge(publicacionExistente);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    // Retorna la lista de publicaciones de la base de datos.
    public List<Publicacion> obtenerPublicaciones() {
        return em.createQuery("SELECT p FROM Publicacion p", Publicacion.class).getResultList();
    }

    // Cierra el EntityManager y la fábrica.
    public void cerrar(){
        if (em.isOpen()){
            em.close();
        }
        if (emf.isOpen()){
            emf.close();
        }
    }
}
