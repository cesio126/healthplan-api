package com.teste.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipo;

    @NotBlank
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;
}