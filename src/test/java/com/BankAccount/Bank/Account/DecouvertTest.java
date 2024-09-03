package com.BankAccount.Bank.Account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DecouvertTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void authorizationDecouvert() throws Exception{
        mockMvc.perform(put("/modifierAutorizationDecouvert")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("authorization", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(2)
    void changeSoldeDecouvert() throws Exception{
        mockMvc.perform(put("/modifierSoldeDecouvert")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("solde", "500"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(3)
    void retraitArgentAvecDecouvert() throws Exception{
        mockMvc.perform(put("/modifierAutorizationDecouvert")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("authorization", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(put("/retrait")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentRetrais", "400"))
                .andExpect(content().string("true"));
    }

    @Test
    @Order(3)
    void retraitNegativeAuthorizationFalse() throws Exception{
        mockMvc.perform(put("/modifierAutorizationDecouvert")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("authorization", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(put("/retrait")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentRetrais", "400"))
                .andExpect(content().string("false"));
    }

    @Test
    @Order(4)
    void retraitSuperieurSolde() throws Exception{
        mockMvc.perform(put("/modifierAutorizationDecouvert")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("authorization", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(put("/retrait")
                        .param("numeroCompte", "FR3227870820626380693911248")
                        .param("argentRetrais", "2000"))
                .andExpect(content().string("false"));
    }
}
