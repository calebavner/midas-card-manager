package ms.cartoes.service;

import lombok.RequiredArgsConstructor;
import ms.cartoes.domain.Cartao;
import ms.cartoes.repo.CartaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repo;

    public Cartao gerarCartao(Cartao c) {
        return repo.save(c);
    }

    public List<Cartao> listarCartoesRendaMenorIgual(Long renda) {
        return repo.findByRendaLessThanEqual(BigDecimal.valueOf(renda));
    }
}
