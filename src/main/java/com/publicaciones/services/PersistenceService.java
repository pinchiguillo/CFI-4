package com.publicaciones.services;

import com.publicaciones.models.Documento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PersistenceService() {
        // Se crea el EntityManager a partir del persistence unit definido en persistence.xml
        emf = Persistence.createEntityManagerFactory("miPU");
        em = emf.createEntityManager();
    }

    // Guarda o actualiza el Documento en la base de datos.
    // Si el documento no tiene ID, se persiste; de lo contrario, se actualiza.
    public boolean guardar(Documento documento) {
        if (documento == null) {
            return false;
        }
        try {
            em.getTransaction().begin();
            if (documento.getId() == null) {
                em.persist(documento);
            } else {
                em.merge(documento);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    // Carga un Documento de la base de datos a partir de su ID.
    public Documento cargar(Long id) {
        return em.find(Documento.class, id);
    }

    // Cierra el EntityManager y la fábrica de EntityManagers.
    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
