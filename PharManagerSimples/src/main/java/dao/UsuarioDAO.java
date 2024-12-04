package dao;

import model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsuarioDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmanagerPU");

    public void cadastrar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public boolean validarLogin(String nomeUsuario, String senha) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nomeUsuario = :nome AND u.senha = :senha", Usuario.class);
        query.setParameter("nome", nomeUsuario);
        query.setParameter("senha", senha);
        
        return !query.getResultList().isEmpty();
    }
}


