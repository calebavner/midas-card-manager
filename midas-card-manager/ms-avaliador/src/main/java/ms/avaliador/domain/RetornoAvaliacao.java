package ms.avaliador.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class RetornoAvaliacao {

    private List<CartoaoAprovado> cartoes;
}
