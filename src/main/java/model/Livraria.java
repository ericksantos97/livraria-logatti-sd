package model;

import java.io.Serializable;

public class Livraria implements Serializable {

    private static final long serialVersionUID = 1390407503750065466L;

    private Integer id;
    private String nome;
    private Livro Livro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public model.Livro getLivro() {
        return Livro;
    }

    public void setLivro(model.Livro livro) {
        Livro = livro;
    }
}
