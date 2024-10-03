package com.teste.service;

import com.teste.exception.ResourceNotFoundException;
import com.teste.models.Beneficiario;
import com.teste.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    public Beneficiario cadastrarBeneficiario(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public List<Beneficiario> listarTodos() {
        return beneficiarioRepository.findAll();
    }

    public Optional<Beneficiario> buscarPorId(Long id) {
        return beneficiarioRepository.findById(id);
    }

    public Beneficiario atualizarBeneficiario(Long id, Beneficiario beneficiarioAtualizado) {
        return beneficiarioRepository.findById(id)
                .map(beneficiario -> {
                    beneficiario.setNome(beneficiarioAtualizado.getNome());
                    beneficiario.setCpf(beneficiarioAtualizado.getCpf());
                    beneficiario.setDataNascimento(beneficiarioAtualizado.getDataNascimento());
                    return beneficiarioRepository.save(beneficiario);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiário não encontrado"));
    }

    public void removerBeneficiario(Long id) {
        beneficiarioRepository.deleteById(id);
    }
}
