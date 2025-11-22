package com.example.sumativa.backend.msresultado.repository;

import com.example.sumativa.backend.model.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisRepository extends JpaRepository<Analisis, Long> {
}