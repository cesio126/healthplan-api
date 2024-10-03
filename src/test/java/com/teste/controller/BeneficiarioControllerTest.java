package com.teste.controller;

import com.teste.HealthPlanApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest(classes = HealthPlanApiApplication.class)
@AutoConfigureMockMvc
public class BeneficiarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String beneficiarioJson;

    @BeforeEach
    public void setup(){

        beneficiarioJson = "{\"nome\": \"João Silva\", \"cpf\": \"12345678900\", \"dataNascimento\": \"1990-01-01\"}";
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveCadastrarBeneficiario_comAutenticacao() throws Exception {

        mockMvc.perform(post("/api/beneficiarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beneficiarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveListarTodosBeneficiarios_comAutenticacao() throws Exception {
        mockMvc.perform(get("/api/beneficiarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beneficiarioJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveBuscarBeneficiarioPorId_comAutenticacao() throws Exception {
        deveCadastrarBeneficiario_comAutenticacao();

        mockMvc.perform(get("/api/beneficiarios/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beneficiarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void deveAtualizarBeneficiario_comAutenticacao() throws Exception {
        String beneficiarioAtualizadoJson = "{\"nome\": \"João Silva Atualizado\", \"cpf\": \"12345678900\", \"dataNascimento\": \"1990-01-01\"}";

        deveCadastrarBeneficiario_comAutenticacao();

        // Assuming the Beneficiario with ID 1 exists
        mockMvc.perform(put("/api/beneficiarios/{id}", 1)
                        .with(httpBasic("user", "password"))  // Mock basic authentication
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beneficiarioAtualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva Atualizado"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void deveDeletarBeneficiario_comAutenticacao() throws Exception {
        deveCadastrarBeneficiario_comAutenticacao();
        mockMvc.perform(delete("/api/beneficiarios/{id}", 1L)
                        .with(httpBasic("user", "password"))  // Mock basic authentication
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());  // Expecting a 204 No Content status
    }
}
