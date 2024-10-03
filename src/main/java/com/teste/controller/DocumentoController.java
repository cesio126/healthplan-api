package com.teste.controller;

import com.teste.exception.ResourceNotFoundException;
import com.teste.models.Documento;
import com.teste.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @PostMapping("/beneficiario/{beneficiarioId}")
    public ResponseEntity<Documento> cadastrarDocumento(@PathVariable Long beneficiarioId, @RequestBody Documento documento) {
        Documento novoDocumento = documentoService.cadastrarDocumento(beneficiarioId, documento);
        return ResponseEntity.ok(novoDocumento);
    }

    @GetMapping("/beneficiario/{beneficiarioId}")
    public ResponseEntity<List<Documento>> listarDocumentosPorBeneficiario(@PathVariable Long beneficiarioId) {
        List<Documento> documentos = documentoService.listarDocumentosPorBeneficiario(beneficiarioId);
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarDocumentoPorId(@PathVariable Long id) {
        Documento documento = documentoService.buscarDocumentoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento não encontrado com ID: " + id));
        return ResponseEntity.ok(documento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> atualizarDocumento(@PathVariable Long id, @RequestBody Documento documentoAtualizado) {
        Documento documento = documentoService.atualizarDocumento(id, documentoAtualizado);
        return ResponseEntity.ok(documento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDocumento(@PathVariable Long id) {
        documentoService.removerDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
