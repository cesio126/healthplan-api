package com.teste.controller;

import com.teste.models.Beneficiario;
import com.teste.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping
    public ResponseEntity<Beneficiario> cadastrarBeneficiario(@RequestBody Beneficiario beneficiario) {
        Beneficiario novoBeneficiario = beneficiarioService.cadastrarBeneficiario(beneficiario);
        return ResponseEntity.ok(novoBeneficiario);
    }

    @GetMapping
    public ResponseEntity<List<Beneficiario>> listarTodos() {
        List<Beneficiario> beneficiarios = beneficiarioService.listarTodos();
        return ResponseEntity.ok(beneficiarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> buscarPorId(@PathVariable Long id) {
        return beneficiarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> atualizarBeneficiario(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizarBeneficiario(id, beneficiario);
        return ResponseEntity.ok(beneficiarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerBeneficiario(@PathVariable Long id) {
        beneficiarioService.removerBeneficiario(id);
        return ResponseEntity.noContent().build();
    }
}
