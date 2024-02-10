package msclientes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import msclientes.domain.Cliente;
import msclientes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static msclientes.common.ClienteConstantes.CLIENTE;
import static msclientes.common.ClienteConstantes.INVALID_CLIENTE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ClienteService service;

    @Test
    public void cadastrarCliente_ComDadosValidos_ReturnaCreated() throws Exception{
        when(service.cadastrar(CLIENTE)).thenReturn(CLIENTE);

        mockMvc.perform(
            post("/clientes")
                .content(mapper.writeValueAsString(CLIENTE))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

    }

    @Test
    public void cadastrarCliente_ComDadosInvalidos_RetornaBadRequest() throws Exception{
        Cliente clienteVazio = new Cliente();

        mockMvc.perform(
                post("/clientes")
                    .content(mapper.writeValueAsString(clienteVazio))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/clientes")
                    .content(mapper.writeValueAsString(INVALID_CLIENTE))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void buscarCliente_ComCpfValido_RetornaCliente() throws Exception{
        when(service.buscarPorCpf(CLIENTE.getCpf())).thenReturn(Optional.of(CLIENTE));

        mockMvc.perform(
            get("/clientes?cpf=" + CLIENTE.getCpf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(CLIENTE));
    }

    @Test
    public void buscarCliente_ComCpfInexistente_RetornaNotFound() throws Exception{
        mockMvc.perform(
                get("/clientes?cpf=" + CLIENTE.getCpf()))
            .andExpect(status().isNotFound());
    }
}
