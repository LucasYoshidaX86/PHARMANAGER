package dao;

import model.Medicamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmanagerPU");

    // Método para Criar um Medicamento
    public void criar(Medicamento medicamento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(medicamento);
        em.getTransaction().commit();
        em.close();
    }

    // Método para Ler um Medicamento pelo ID
    public Medicamento ler(Long id) {
        EntityManager em = emf.createEntityManager();
        Medicamento medicamento = em.find(Medicamento.class, id);
        em.close();
        return medicamento;
    }

    // Método para Listar todos os Medicamentos
    public List<Medicamento> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Medicamento> medicamentos = em.createQuery("FROM Medicamento", Medicamento.class).getResultList();
        em.close();
        return medicamentos;
    }

    // Método para Atualizar um Medicamento
    public void atualizar(Medicamento medicamento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(medicamento);
        em.getTransaction().commit();
        em.close();
    }

    // Método para Excluir um Medicamento
    public boolean excluir(Long idMedicamento) {
        EntityManager em = emf.createEntityManager();
        Medicamento medicamento = em.find(Medicamento.class, idMedicamento);
        if (medicamento != null) {
            em.getTransaction().begin();
            em.remove(medicamento);
            em.getTransaction().commit();
        }
        em.close();
		return false;
    }

    // Método para Buscar Medicamentos por Tipo
    public List<Medicamento> buscarPorTipo(String tipo) {
        EntityManager em = emf.createEntityManager();
        try {
            // Usar consulta dinâmica baseada no tipo
            return em.createQuery("SELECT m FROM Medicamento m WHERE TYPE(m) = :tipo", Medicamento.class)
                     .setParameter("tipo", Class.forName("model." + tipo))
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    // Método para salvar um Medicamento
    public boolean salvar(Medicamento medicamento) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(medicamento);
            em.getTransaction().commit();
            return true; // Retorna verdadeiro se salvar com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna falso se ocorrer erro
        } finally {
            em.close();
        }
    }
}




