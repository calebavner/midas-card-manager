package ms.cartoes.service;

import lombok.RequiredArgsConstructor;
import ms.cartoes.domain.ClienteCartao;
import ms.cartoes.repo.ClienteCartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repo;

    public List<ClienteCartao> listarCartoesPorCpf(String cpf) {
        return repo.findByCpf(cpf);
    }
}
