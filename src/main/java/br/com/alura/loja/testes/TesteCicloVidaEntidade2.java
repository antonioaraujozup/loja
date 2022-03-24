package br.com.alura.loja.testes;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class TesteCicloVidaEntidade2 {

    public static void main(String[] args) {

        // Cria uma categoria.
        Categoria categoria = new Categoria("CELULARES");

        // Cria um EntityManager.
        EntityManager em = JPAUtil.getEntityManager();

        // Inicia a transação.
        em.getTransaction().begin();

        // Persiste a entidade.
        // (Transient --> Managed)
        em.persist(categoria);
        categoria.setNome("INFORMATICA");

        // Faz o commit da transação.
        // (sincroniza entidade com o banco de dados e finaliza a transação)
        em.getTransaction().commit();

        // Fecha o EntityManager.
        // (Managed --> Detached)
        em.close();

        // É esperado 1 insert e 1 update.

    }

}
