package main;

import exception.RecursoNaoEncontrado;
import model.Livraria;
import model.Livro;
import connect.ConexaoDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {

    private static ConexaoDatabase connection;

    public static void main(final String[] args) {
        popularDatabase();
        buscarTodos();
    }

    private static void popularDatabase() {

        Livro livro = new Livro();
        livro.setIsbn(14356);
        livro.setTitulo("Java Fundamentals");
        livro.setAutor("James Gosling");
        livro.setAnoLancamento(2001);
        livro.setQuantidadePaginas(800);
        livro.setGenero("Tecnologia");
        inserirLivro(livro);

        Livraria livraria = new Livraria();
        livraria.setNome("Livraria Saraiva");
        inserirLivraria(livraria, livro.getIsbn());

        Livro livro2 = new Livro();
        livro2.setIsbn(19455);
        livro2.setTitulo("O mistério mundo de Jack");
        livro2.setAutor("Tim Burton");
        livro2.setAnoLancamento(2007);
        livro2.setQuantidadePaginas(280);
        livro2.setGenero("Ficção Cientifica");
        inserirLivro(livro2);

        Livraria livraria2 = new Livraria();
        livraria2.setNome("Livraria Saraiva");
        inserirLivraria(livraria2, livro2.getIsbn());

    }

    private static void inserirLivro(final Livro livro) {
        try {
            connection = new ConexaoDatabase();
            connection.execute(String.format("insert into livro (isbn, titulo, autor, ano_lancamento, quantidade_paginas, genero) " +
                            "values ('%s', '%s', '%s', '%s', '%s', '%s')",
                    livro.getIsbn().toString(), livro.getTitulo(),
                    livro.getAutor(), livro.getAnoLancamento().toString(),
                    livro.getQuantidadePaginas().toString(), livro.getGenero()));
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
    }

    private static void inserirLivraria(final Livraria livraria, final Integer isbn) {
        try {
            connection = new ConexaoDatabase();
            connection.execute(String.format("insert into livraria (nome, isbn_livro) " +
                    "values ('%s', '%s')", livraria.getNome(), isbn.toString()));
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
    }

    private static void buscarTodos() {
        try {
            connection = new ConexaoDatabase();
            ResultSet rs = connection.executeWithResponse(
                    "select " +
                            "lvra.id, " +
                            "lvra.nome, " +
                            "lvr.isbn, " +
                            "lvr.titulo, " +
                            "lvr.autor, " +
                            "lvr.ano_lancamento, " +
                            "lvr.quantidade_paginas, " +
                            "lvr.genero " +
                            "from livraria as lvra " +
                            "inner join livro as lvr on lvr.isbn = lvra.isbn_livro " +
                            "order by lvra.nome asc");

            while (rs.next()) {
                System.out.println(String.format(
                        "\nLivraria - ID %d: , Nome: %s" +
                                "\nLivro - ISBN: %d, Título: %s, Autor: %s, Ano de Lançamento: %d, " +
                                "Quantidade de Páginas: %d, Gênero: %s",
                        rs.getInt("id"), rs.getString("nome"),
                        rs.getInt("isbn"), rs.getString("titulo"),
                        rs.getString("autor"), rs.getInt("ano_lancamento"),
                        rs.getInt("quantidade_paginas"), rs.getString("genero")));
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        } catch (final RecursoNaoEncontrado e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
    }

}
