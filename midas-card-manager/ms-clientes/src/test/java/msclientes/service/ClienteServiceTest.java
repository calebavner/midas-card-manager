package msclientes.service;

import msclientes.domain.Cliente;
import msclientes.repo.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static msclientes.common.ClienteConstantes.CLIENTE;
import static msclientes.common.ClienteConstantes.INVALID_CLIENTE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteRepository repo;

    @Test
    public void cadastraCliente_ComDadosValidos_RetornaCliente() {

        when(repo.save(CLIENTE)).thenReturn(CLIENTE);
        Cliente sut = service.cadastrar(CLIENTE);
        assertThat(sut).isEqualTo(CLIENTE);
    }

    @Test
    public void cadastraCliente_ComDadosInvalidos_ThrowsException() {

        when(repo.save(INVALID_CLIENTE)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> service.cadastrar(INVALID_CLIENTE)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void buscarCliente_ComCpfValido_RetornaCliente() {
        when(repo.findByCpf(CLIENTE.getCpf())).thenReturn(Optional.of(CLIENTE));
        Optional<Cliente> sut = service.buscarPorCpf(CLIENTE.getCpf());
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(CLIENTE);
    }

    @Test
    public void buscarCliente_ComCpfErrado_SemRetorno() {
        when(repo.findByCpf(INVALID_CLIENTE.getCpf())).thenReturn(Optional.empty());
        Optional<Cliente> sut = service.buscarPorCpf(INVALID_CLIENTE.getCpf());
        assertThat(sut).isEmpty();
    }
    @Test
    public void buscarCliente_ComCpfVazio_SemRetorno() {
        String cpf = "";
        when(repo.findByCpf(cpf)).thenReturn(Optional.empty());
        Optional<Cliente> sut = service.buscarPorCpf(cpf);
        assertThat(sut).isEmpty();
    }
}