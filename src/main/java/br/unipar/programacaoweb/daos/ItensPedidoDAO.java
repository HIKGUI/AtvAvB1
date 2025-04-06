package br.unipar.programacaoweb.daos;

import br.unipar.programacaoweb.models.ItensPedido;
import br.unipar.programacaoweb.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ItensPedidoDAO {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(ItensPedido itensPedido) {
        EntityManager em = EntityManagerUtil.getEm();
        try {
            em.getTransaction().begin();
            em.persist(itensPedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(ItensPedido itensPedido) {
        try {
            em.getTransaction().begin();
            em.merge(itensPedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public boolean excluir(Integer id) {
        EntityManager em = EntityManagerUtil.getEm();

        try {
            em.getTransaction().begin();
            ItensPedido itensPedido = em.find(ItensPedido.class, id);
            em.remove(itensPedido);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public ItensPedido buscarPorId(Integer id) {
        try {
            return em.find(ItensPedido.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public ItensPedido buscarPorItensPedido(String id) {
        try {
            return em.createQuery("from ItensPedido where id = :id", ItensPedido.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<ItensPedido> buscarTodos() {
        try {
            return em.createQuery("from ItensPedido", ItensPedido.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }


}
