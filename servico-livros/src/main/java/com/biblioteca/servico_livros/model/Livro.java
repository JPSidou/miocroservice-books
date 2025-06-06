package com.biblioteca.servico_livros.model;

public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidade;
    private boolean disponivel;
    
    public Livro(String id, String titulo, String autor, int anoPublicacao, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidade = quantidade;
        this.disponivel = true; 
    }
    
    

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anoPublicacao=" + anoPublicacao
                + ", disponivel=" + disponivel + "]";
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getTitulo() {
        return titulo;
    }



    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getAutor() {
        return autor;
    }



    public void setAutor(String autor) {
        this.autor = autor;
    }



    public int getAnoPublicacao() {
        return anoPublicacao;
    }



    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }



    public int getQuantidade() {
        return quantidade;
    }



    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }



    public boolean isDisponivel() {
        return disponivel;
    }



    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}