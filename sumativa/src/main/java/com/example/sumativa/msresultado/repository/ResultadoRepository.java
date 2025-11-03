package com.example.sumativa.msresultado.repository;

import com.example.sumativa.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

}