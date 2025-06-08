package com.biblioteca.servico_livros.repository;

import com.biblioteca.servico_livros.model.Livro;
import org.springframework.data.mongodb.repository.MongoRepository;

// Interface mágica que nos dá os métodos CRUD para Livros
public interface LivroRepository extends MongoRepository<Livro, String> {
}