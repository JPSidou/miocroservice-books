// Em src/main/java/com/biblioteca/servico_livros/service/LivroService.java
package com.biblioteca.servico_livros.service;

import com.biblioteca.servico_livros.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final List<Livro> livros = new ArrayList<>();

    public Livro adicionarLivro(Livro novoLivro) {
        livros.add(novoLivro);
        return novoLivro;
    }

    public List<Livro> listarTodos() {
        return livros;
    }

    public Optional<Livro> buscarPorId(String id) {
        return livros.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst();
    }
    
    public Optional<Livro> alterarDisponibilidade(String id, boolean disponivel) {
        Optional<Livro> livroOptional = buscarPorId(id);
        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            if (livro.getQuantidade() == 0) {
                livro.setDisponivel(disponivel);
            }
            return Optional.of(livro);
        }
        return Optional.empty();
    }

    public boolean removerLivro(String id) {
        return livros.removeIf(livro -> livro.getId().equals(id) && livro.isDisponivel());
    }

    public boolean verificarDisponibilidade(String id) {
        return buscarPorId(id)
                .map(Livro::isDisponivel)
                .orElse(false); // ou lançar exceção, se preferir
    }

}