package ms.cartoes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorCliente {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorCliente fromModel(ClienteCartao model) {
        return new CartoesPorCliente(
            model.getCartao().getNome(),
            model.getCartao().getBandeira().toString(),
            model.getCartao().getLimiteInicial()
        );
    }
}
