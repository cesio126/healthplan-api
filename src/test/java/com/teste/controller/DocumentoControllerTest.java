package com.teste.controller;

import com.teste.HealthPlanApiApplication;
import com.teste.models.Beneficiario;
import com.teste.models.Documento;
import com.teste.service.BeneficiarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest(classes = HealthPlanApiApplication.class)
@ContextConfiguration(classes = BeneficiarioService.class)
@AutoConfigureMockMvc
public class DocumentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @BeforeEach
    public void ini(){

        beneficiarioService.cadastrarBeneficiario(Beneficiario.builder()
                .cpf("12345678900")
                .nome("João Silva")
                .dataNascimento("1990-01-01")
                .build());
    }

    // Test: Create a Documento with Basic Authentication
    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveCadastrarDocumento_comAutenticacao() throws Exception {
        String documentoJson = "{\"tipo\": \"RG\", \"numero\": \"12345678\"}";

        mockMvc.perform(post("/api/documentos/beneficiario/{beneficiarioId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(documentoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("RG"))
                .andExpect(jsonPath("$.numero").value("12345678"));
    }

    // Test: List all Documentos for a Beneficiario with authentication
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void deveListarDocumentosPorBeneficiario_comAutenticacao() throws Exception {
        mockMvc.perform(get("/api/documentos/beneficiario/{beneficiarioId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    // Test: Retrieve a Documento by ID with Basic Authentication
    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveBuscarDocumentoPorId_comAutenticacao() throws Exception {
        deveCadastrarDocumento_comAutenticacao();

        mockMvc.perform(get("/api/documentos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipo").exists())
                .andExpect(jsonPath("$.numero").exists());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveAtualizarDocumento_comAutenticacao() throws Exception {
        String documentoAtualizadoJson = "{\"tipo\": \"CNH\", \"numero\": \"98765432\"}";

        deveCadastrarDocumento_comAutenticacao();

        mockMvc.perform(put("/api/documentos/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(documentoAtualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("CNH"))
                .andExpect(jsonPath("$.numero").value("98765432"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveDeletarDocumento_comAutenticacao() throws Exception {

        deveCadastrarDocumento_comAutenticacao();

        mockMvc.perform(delete("/api/documentos/{id}", 1)
                        .with(httpBasic("user", "password"))  // Mock basic authentication
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());  // Expecting a 204 No Content status
    }
}