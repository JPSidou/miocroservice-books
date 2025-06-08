package com.biblioteca.servico_livros.service;

import com.biblioteca.servico_livros.model.Livro;
import com.biblioteca.servico_livros.repository.LivroRepository; // Importe o repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    // Injetamos o repositório do MongoDB
    @Autowired
    private LivroRepository livroRepository;

    public Livro adicionarLivro(Livro novoLivro) {
        // O método save cria se o ID é novo, ou atualiza se já existe
        return livroRepository.save(novoLivro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(String id) {
        return livroRepository.findById(id);
    }

    public boolean removerLivro(String id) {
        Optional<Livro> livroOpt = livroRepository.findById(id);
        if (livroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            // Mantendo sua regra de negócio: só remove se disponível
            if (livro.isDisponivel()) {
                livroRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    // Método para ser chamado pelo serviço de aluguel
    public Optional<Livro> atualizarQuantidade(String id, int novaQuantidade) {
        return livroRepository.findById(id).map(livro -> {
            livro.setQuantidade(novaQuantidade);
            livro.setDisponivel(novaQuantidade > 0); // Disponibilidade é um reflexo da quantidade
            return livroRepository.save(livro); // Salva o estado atualizado no banco
        });
    }

    public boolean verificarDisponibilidade(String id) {
        return livroRepository.findById(id)
                .map(Livro::isDisponivel)
                .orElse(false);
    }
}