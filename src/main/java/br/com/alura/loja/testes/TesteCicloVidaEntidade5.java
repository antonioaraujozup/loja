package br.com.alura.loja.testes;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class TesteCicloVidaEntidade5 {

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

        // Sincroniza entidade com o banco de dados.
        em.flush();

        // Clear.
        // (Managed --> Detached)
        em.clear();

        // Merge.
        // (Detached --> Managed)
        categoria = em.merge(categoria);

        // Esta alteração agora será realizada.
        categoria.setNome("LIVROS");

        // Sincroniza entidade com o banco de dados.
        em.flush();

        // É esperado 1 insert, 1 select e 2 updates.

    }

}
