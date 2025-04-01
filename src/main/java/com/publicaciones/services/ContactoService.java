package com.publicaciones.services;

import com.publicaciones.models.Contacto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ContactoService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ContactoService() {
        // Se crea el EntityManager a partir del persistence unit definido en persistence.xml
        emf = Persistence.createEntityManagerFactory("miPU");
        em = emf.createEntityManager();
    }

    // Crea un nuevo contacto en la base de datos
    public boolean agregarContacto(Contacto contacto) {
        try {
            em.getTransaction().begin();
            em.persist(contacto);
            em.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }

    // Obtiene la lista de todos los contactos
    public List<Contacto> obtenerTodos() {
        return em.createQuery("SELECT c FROM Contacto c", Contacto.class).getResultList();
    }

    // Actualiza un contacto existente identificado por su id
    public boolean actualizarContacto(Long id, String nombre, String email, String telefono) {
        try {
            em.getTransaction().begin();
            Contacto contacto = em.find(Contacto.class, id);
            if (contacto != null) {
                contacto.setNombre(nombre);
                contacto.setEmail(email);
                contacto.setTelefono(telefono);
                em.merge(contacto);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch(Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }

    // Elimina un contacto a partir de su id
    public boolean eliminarContacto(Long id) {
        try {
            em.getTransaction().begin();
            Contacto contacto = em.find(Contacto.class, id);
            if (contacto != null) {
                em.remove(contacto);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch(Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }

    // Cierra el EntityManager y su fábrica
    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()){
            emf.close();
        }
    }
}
