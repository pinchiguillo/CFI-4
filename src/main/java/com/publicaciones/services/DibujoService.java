package com.publicaciones.services;

import com.publicaciones.models.Dibujo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class DibujoService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public DibujoService() {
        emf = Persistence.createEntityManagerFactory("miPU");
        em = emf.createEntityManager();
    }

    // Guarda (o actualiza) un dibujo en la base de datos.
    public boolean guardarDibujo(Dibujo dibujo) {
        if (dibujo == null) {
            return false;
        }
        try {
            em.getTransaction().begin();
            if (dibujo.getId() == null) {
                em.persist(dibujo);
            } else {
                em.merge(dibujo);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }

    // Elimina un dibujo dado su id.
    public boolean eliminarDibujo(Long id) {
        try {
            em.getTransaction().begin();
            Dibujo dibujo = em.find(Dibujo.class, id);
            if (dibujo == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(dibujo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }

    // Lista todos los dibujos guardados en la base de datos.
    // Usamos la API de Hibernate a través de JPA (también es válido usar em.createQuery()).
    public List<Dibujo> listarDibujos() {
        return em.createQuery("FROM Dibujo", Dibujo.class).getResultList();
    }

    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()){
            emf.close();
        }
    }
}
