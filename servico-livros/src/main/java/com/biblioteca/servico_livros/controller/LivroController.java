package com.biblioteca.servico_livros.controller;

import com.biblioteca.servico_livros.model.Livro;
import com.biblioteca.servico_livros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Endpoint: GET /livros -> Lista todos os livros
    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarTodos();
    }

    // Endpoint: GET /livros/{id} -> Busca um livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable String id) {
        return livroService.buscarPorId(id)
                .map(livro -> ResponseEntity.ok(livro))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint: POST /livros -> Adiciona um novo livro
    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.adicionarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }
    
    // Endpoint: PATCH /livros/{id} -> Atualiza atributos do livro, como a quantidade.
    // Espera um corpo de requisição como: { "quantidade": 5 }
    @PatchMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        if (updates.containsKey("quantidade")) {
            int novaQuantidade = (Integer) updates.get("quantidade");
            Optional<Livro> livroAtualizado = livroService.atualizarQuantidade(id, novaQuantidade);
            return livroAtualizado
                    .map(livro -> ResponseEntity.ok(livro))
                    .orElse(ResponseEntity.notFound().build());
    }
    return ResponseEntity.badRequest().build();
}
        // Endpoint: GET /livros/{id}/disponivel -> Verifica se o livro está disponível
    @GetMapping("/{id}/disponivel")
    public ResponseEntity<Boolean> verificarDisponibilidade(@PathVariable String id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        boolean disponivel = livro.get().isDisponivel();
        return ResponseEntity.ok(disponivel);
    }

    // Endpoint: DELETE /livros/{id} -> Remove um livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable String id) {
        if (livroService.removerLivro(id)) {
            return ResponseEntity.noContent().build(); // Sucesso, sem conteúdo de retorno
        }
        // Isso pode acontecer se o livro não existe OU não está disponível
        return ResponseEntity.notFound().build();
    }


}