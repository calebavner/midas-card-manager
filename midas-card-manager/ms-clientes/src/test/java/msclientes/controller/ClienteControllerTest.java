package msclientes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import msclientes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static msclientes.common.ClienteConstantes.CLIENTE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ClienteService service;

    @Test
    public void cadastrarCliente_ComDadosValidos_RetornaCreated() throws Exception{

        when(service.cadastrar(CLIENTE)).thenReturn(CLIENTE);

        mockMvc.perform(
                post("/clientes").content(mapper.writeValueAsString(CLIENTE)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }
}