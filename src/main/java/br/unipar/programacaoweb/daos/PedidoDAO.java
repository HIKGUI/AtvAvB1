package br.unipar.programacaoweb.daos;

import br.unipar.programacaoweb.models.Pedido;
import br.unipar.programacaoweb.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoDAO {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Pedido pedido) {
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Pedido pedido) {
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public boolean excluir(Integer id) {
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, id);
            em.remove(pedido);
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

    public Pedido buscarPorId(Integer id) {
        try {
            return em.find(Pedido.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Pedido buscarPorPedido(String id) {
        try {
            return em.createQuery("from Pedido where id = :id", Pedido.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Pedido> buscarTodos() {
        try {
            return em.createQuery("from Pedido", Pedido.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
