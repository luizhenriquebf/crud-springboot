package com.luiz.crud.model.repository;

import java.util.List;

import com.luiz.crud.model.entity.Filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository 
    extends JpaRepository<Filme, Long> { 
        
        public List<Filme> findByNomeContaining(String nome);

        @Query("SELECT f FROM Filme f WHERE f.nota >= :nota ")
        public List<Filme> findByNotaGreaterThan(@Param("nota") double nota);
    }  

