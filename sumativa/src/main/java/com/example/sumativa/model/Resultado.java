package com.example.sumativa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "RESULTADO")
@Data
public class Resultado {

    @Id
    @Column(name = "ID_RESULTADO")
    private Long idResultado;

    @Column(name = "FECHA_ANALISIS")
    private Date fechaAnalisis;

    @Column(name = "RESULTADO_DETALLE")
    private String resultadoDetalle;

    // Relaciones ManyToOne
    @ManyToOne
    @JoinColumn(name = "ID_ANALISIS", nullable = false)
    private Analisis analisis;

    @ManyToOne
    @JoinColumn(name = "ID_LABORATORIO", nullable = false)
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_TECNICO")
    private Usuario usuarioTecnico;

    public Resultado() {
    }
}