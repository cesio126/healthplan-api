package com.teste.repository;

import com.teste.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByBeneficiarioId(Long beneficiarioId);
}
