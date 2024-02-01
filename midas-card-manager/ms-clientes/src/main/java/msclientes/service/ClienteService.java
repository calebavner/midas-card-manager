package msclientes.service;

import lombok.RequiredArgsConstructor;
import msclientes.domain.Cliente;
import msclientes.repo.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repo;
    @Transactional
    public Cliente cadastrar(Cliente c) {
        return repo.save(c);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return repo.findByCpf(cpf);
    }
}
