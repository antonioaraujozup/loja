package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        // Cadastra o produto.
        cadastrarProduto();

        // Cria o EntityManager.
        EntityManager em = JPAUtil.getEntityManager();

        // Cria o dao.
        ProdutoDao produtoDao = new ProdutoDao(em);

        // Busca o produto por Id.
        Produto produto = produtoDao.buscarPorId(1L);

        // Imprime o preço do produto buscado.
        System.out.println(produto.getPreco());

        // Busca todos os produtos cadastrados.
        List<Produto> produtos = produtoDao.buscarTodos();

        // Imprime a quantidade de produtos cadastrados.
        System.out.println(produtos.size());

        // Busca produtos por nome.
        List<Produto> produtosPorNome = produtoDao.buscarPorNome("Xiaomi Redmi");

        // Imprime nome dos produtos.
        produtosPorNome.forEach(p -> System.out.println(p.getNome()));

        // Busca produtos por nome da categoria.
        List<Produto> produtosPorNomeCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");

        // Imprime nome dos produtos.
        produtosPorNomeCategoria.forEach(p -> System.out.println(p.getNome()));

        // Busca preço por nome de um produto.
        BigDecimal preco = produtoDao.buscarPrecoPorNomeProduto("Xiaomi Redmi");

        // Imprime o preço do produto.
        System.out.println("O preço do produto é: R$" + preco);

    }

    private static void cadastrarProduto() {

        // Cria a categoria.
        Categoria celulares = new Categoria("CELULARES");

        // Cria o produto.
        Produto celular = new Produto(
                "Xiaomi Redmi",
                "Muito legal",
                new BigDecimal("800"),
                celulares
        );

        // Cria um EntityManager.
        EntityManager em = JPAUtil.getEntityManager();

        // Cria os DAOs.
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        // Inicia a transação.
        em.getTransaction().begin();

        // Persiste a categoria (celulares).
        em.persist(celulares);

        // Persiste o produto (celular).
        produtoDao.cadastrar(celular);

        // Faz o commit da transação.
        em.getTransaction().commit();

        // Fecha o EntityManager.
        em.close();

    }

}