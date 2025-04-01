package br.unipar.programacaoweb.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private EntityManagerUtil() {}

    public static EntityManagerFactory getEmf() {
       if (emf == null) {
           emf = Persistence.
                   createEntityManagerFactory("serviceCepPU");
           System.out.println("Conexão Aberta!");
       }
       return emf;
    }

    public static EntityManager getEm() {
       if (emf == null) {
           getEmf();
       }
       if (em == null || !em.isOpen()) {
           em = emf.createEntityManager();
           System.out.println("Entity Manager Criado!");
       }
       return em;
    }

}
