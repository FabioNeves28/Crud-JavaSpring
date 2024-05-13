package controller;

import model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.LivroRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class LivroController {

    @Autowired
    private LivroRepo livroRepo;

    @GetMapping
    public ResponseEntity<List<Livro>> getLivros() {
        try {
            List<Livro> livrosList = new ArrayList<>();
            livroRepo.findAll().forEach(livrosList::add);
            if (livrosList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(livrosList, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/getLivroById/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
        Optional<Livro> livro = livroRepo.findById(id);
        if (livro.isPresent()) {
            return new ResponseEntity<>(livro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addLivro")
    public ResponseEntity<Livro> addLivro(@RequestBody Livro livro) {
       Livro livroObj = livroRepo.save(livro);

       return new ResponseEntity<>(livroObj, HttpStatus.OK);
    }
    @PostMapping("updateLivroById/{id}")
    public ResponseEntity<Livro> updateLivroById(@PathVariable Long id, @RequestBody Livro novoLivroData) {
          Optional<Livro> antigoLivroData = livroRepo.findById(id);
            if (antigoLivroData.isPresent()) {
                Livro livro = antigoLivroData.get();
                livro.setTitle(novoLivroData.getTitle());
                livro.setAuthor(novoLivroData.getAuthor());
                Livro livroObj = livroRepo.save(livro);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteLivro/{id}")
    public ResponseEntity<Livro> deleteLivro(@PathVariable Long id) {
        livroRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
