package br.unipar.programacaoweb.daos;

import br.unipar.programacaoweb.models.Usuario;
import br.unipar.programacaoweb.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioDAO {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.merge(usuario);
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
            Usuario usuario = em.find(Usuario.class, id);
            em.remove(usuario);
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

    public Usuario buscarPorId(Integer id) {
        try {
            return em.find(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario buscarPorNome(String nome) {
        try {
            return em.createQuery("from Usuario where nome = :nome", Usuario.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> buscarTodos() {
        try {
            return em.createQuery("from Usuario", Usuario.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
