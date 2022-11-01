package dev.carlosandrade.microservice.mercadofinanceiro.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.carlosandrade.microservice.mercadofinanceiro.MercadoFinanceiroApplication;
import dev.carlosandrade.microservice.mercadofinanceiro.entity.Ativo;
import dev.carlosandrade.microservice.mercadofinanceiro.repository.AtivoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = MercadoFinanceiroApplication.class)
@AutoConfigureMockMvc
class AtivosControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AtivoRepository repository;

    @Test
    void getAll() throws Exception {
        //Arrange
        RequestBuilder request = MockMvcRequestBuilders.get("/ativos");
        //Act
        MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
        //Assert

    }

    @Test
    void getCreate() throws Exception {

        Ativo ativo = Ativo.builder().ticker("VALE3").nome("VALE ON").tipoAtivo("Ações").build();

        String jsonContent = mapper.writeValueAsString(ativo);

        //Arrange
        RequestBuilder request = MockMvcRequestBuilders.post("/ativos")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        //Act
        MvcResult result = mvc.perform(request).andReturn();

        //Assert

        if (result.getResponse().getStatus() != HttpStatus.OK.value()) {
            String retornoObjetoJaExiste = "O  ativo " + ativo.getTicker() + " ja esta cadastrado no sistema";
            String returnEntity = result.getResponse().getContentAsString();
        }
    }

    @Test
    void getRead() throws Exception {

        String ticker = "VALE3";
        //Arrange
        RequestBuilder request = MockMvcRequestBuilders.get("/ativos/" + ticker);
        //Act
        MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
        //Assert

    }

}
