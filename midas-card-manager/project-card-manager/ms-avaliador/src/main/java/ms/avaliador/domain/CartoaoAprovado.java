package ms.avaliador.domain;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartoaoAprovado {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
