package com.luiz.crud.controller;

import java.util.List;

import javax.validation.Valid;

import com.luiz.crud.model.entity.Filme;
import com.luiz.crud.model.repository.FilmeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    
    FilmeRepository repository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.repository = filmeRepository;
    }

    @PostMapping
    public Filme create(@Valid @RequestBody Filme filme) {
        return repository.save(filme);
    }

    @GetMapping
    public List<Filme> findALl() {
        return repository.findAll();
    }

    @GetMapping(path = "/filter", params = "nota")
    public List<Filme> findNotaGreaterThan(@RequestParam("nota") double nota) {
        return repository.findByNotaGreaterThan(nota);
    }

    @GetMapping(path = "/filter", params = "nome")
    public List<Filme> findByName(@RequestParam("nome") String nome) {
        return repository.findByNomeContaining(nome);
    }
    
    @GetMapping(path = "/filter", params = "pagina")
    public Page<Filme> findP(@RequestParam("pagina") int pagina) {
        PageRequest nPage = PageRequest.of(pagina, 2);
        return repository.findAll(nPage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Filme> findByEntity(@PathVariable Long id) {
        return repository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> update(@PathVariable Long id,@Valid @RequestBody Filme filme) {
        return repository.findById(id)
        .map(record -> {
            record.setNome(filme.getNome());
            record.setGenero(filme.getGenero());
            record.setNota(filme.getNota());
            Filme updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repository.findById(id)
        .map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        })
        .orElse(ResponseEntity.notFound().build());
    }
    
}