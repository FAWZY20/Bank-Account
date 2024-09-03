package com.BankAccount.Bank.Account;

import com.BankAccount.Bank.Account.Domain.model.CompteBancaire;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompteBancaireTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    CompteBancaire compteBancaire = new CompteBancaire(200);

    @Test
    @Order(1)
    void ajouterCompte() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(compteBancaire);
        mockMvc.perform(post("/ajoutCompte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(2)
    void depotArgent() throws Exception{
        mockMvc.perform(put("/depot")
                .param("numeroCompte", "FR3227870820626380693911248")
                .param("argentDeposer", "200"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(3)
    void retraitArgent() throws Exception{
        mockMvc.perform(put("/retrait")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentRetrais", "200"))
                .andExpect(content().string("true"));
    }

    @Test
    @Order(4)
    void retraitArgentRefuser() throws Exception{
        mockMvc.perform(put("/retrait")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentRetrais", "1000"))
                .andExpect(content().string("false"));
    }

}
