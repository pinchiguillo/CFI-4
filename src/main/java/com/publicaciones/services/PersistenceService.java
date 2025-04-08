package com.publicaciones.services;

import com.publicaciones.models.Documento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PersistenceService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PersistenceService() {
        // Crea el EntityManager a partir del persistence unit
        emf = Persistence.createEntityManagerFactory("miPU");
        em = emf.createEntityManager();
    }

    public boolean guardar(Documento documento) {
        if (documento == null) {
            return false;
        }
        try {
            em.getTransaction().begin();
            if (documento.getId() == null) {
                Documento existente = cargarPorNombreArchivo(documento.getNombreArchivo());
                if (existente != null) {
                    existente.setContenido(documento.getContenido());
                    existente.setEnEdicion(documento.isEnEdicion());
                    em.merge(existente);
                } else {
                    em.persist(documento);
                }
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

    public Documento cargar(Long id) {
        return em.find(Documento.class, id);
    }

    public Documento cargarPorNombreArchivo(String nombreArchivo) {
        List<Documento> docs = em.createQuery(
                        "SELECT d FROM Documento d WHERE d.nombreArchivo = :nombreArchivo",
                        Documento.class
                ).setParameter("nombreArchivo", nombreArchivo)
                .getResultList();
        if (docs.isEmpty()) {
            return null;
        } else if (docs.size() == 1) {
            return docs.get(0);
        } else {
            System.err.println("Warning: múltiples documentos con el nombre " + nombreArchivo + ". Devolviendo el primero.");
            return docs.get(0);
        }
    }

    // NUEVO: Listar todos los documentos
    public List<Documento> listarTodos() {
        return em.createQuery("SELECT d FROM Documento d", Documento.class)
                .getResultList();
    }

    public boolean eliminarPorNombreArchivo(String nombreArchivo) {
        try {
            Documento doc = cargarPorNombreArchivo(nombreArchivo);
            if (doc == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(em.contains(doc) ? doc : em.merge(doc));
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

    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
