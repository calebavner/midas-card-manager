package ms.cartoes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ms.cartoes.domain.enums.BandeiraCartao;

import java.math.BigDecimal;

@Entity
@Table(name = "cartoes")
@Data
@NoArgsConstructor
public class Cartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteInicial;

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteInicial) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteInicial = limiteInicial;
    }
}
