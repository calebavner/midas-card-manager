package msclientes.service;

import msclientes.domain.Cliente;
import msclientes.repo.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }


    public Cliente cadastrar(Cliente c) {
        return repo.save(c);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return repo.findByCpf(cpf);
    }
}
