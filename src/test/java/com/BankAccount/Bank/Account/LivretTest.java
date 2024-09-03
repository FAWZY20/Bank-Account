package com.BankAccount.Bank.Account;

import com.BankAccount.Bank.Account.Domain.model.Livret;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LivretTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Livret livret = new Livret("FR3227870820626380693911248",200,1500);

    @Test
    @Order(1)
    void ajouterLivretEtSoldeTest() throws Exception{
        mockMvc.perform(put("/modifierAutorizationDecouvert")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("authorization", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(put("/depot")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentDeposer", "1000"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonRequest = objectMapper.writeValueAsString(livret);
        mockMvc.perform(post("/ajoutLivret")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(content().string("true"));

        mockMvc.perform(put("/depotLivre")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentDeposer", "200"))
                .andExpect(content().string("true"));

        mockMvc.perform(delete("/supLivret")
                .param("numeroCompte", "FR3227870820626380693911248"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
