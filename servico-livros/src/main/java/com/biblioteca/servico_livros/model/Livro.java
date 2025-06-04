package com.biblioteca.servico_livros.model;

public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean disponivel;
    
    public Livro(String id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true; 
    }
    
    public String getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anoPublicacao=" + anoPublicacao
                + ", disponivel=" + disponivel + "]";
    }
}