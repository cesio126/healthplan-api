package com.teste.service;

import com.teste.exception.ResourceNotFoundException;
import com.teste.models.Documento;
import com.teste.repository.BeneficiarioRepository;
import com.teste.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    public Documento cadastrarDocumento(Long beneficiarioId, Documento documento) {
        return beneficiarioRepository.findById(beneficiarioId)
                .map(beneficiario -> {
                    documento.setBeneficiario(beneficiario);  // Associar o documento ao beneficiário
                    return documentoRepository.save(documento);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiário com id " + beneficiarioId + " não encontrado"));
    }

    public List<Documento> listarDocumentosPorBeneficiario(Long beneficiarioId) {
        if (!beneficiarioRepository.existsById(beneficiarioId)) {
            throw new ResourceNotFoundException("Beneficiário com id " + beneficiarioId + " não encontrado");
        }
        return documentoRepository.findByBeneficiarioId(beneficiarioId);
    }

    /**
     * Busca um documento por ID.
     *
     * @param documentoId  ID do documento
     * @return O documento encontrado
     */
    public Optional<Documento> buscarDocumentoPorId(Long documentoId) {
        return documentoRepository.findById(documentoId);
    }

    public Documento atualizarDocumento(Long documentoId, Documento documentoAtualizado) {
        return documentoRepository.findById(documentoId)
                .map(documento -> {
                    documento.setTipo(documentoAtualizado.getTipo());
                    documento.setNumero(documentoAtualizado.getNumero());
                    return documentoRepository.save(documento);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Documento com id " + documentoId + " não encontrado"));
    }

    public void removerDocumento(Long documentoId) {
        if (!documentoRepository.existsById(documentoId)) {
            throw new ResourceNotFoundException("Documento com id " + documentoId + " não encontrado");
        }
        documentoRepository.deleteById(documentoId);
    }
}
