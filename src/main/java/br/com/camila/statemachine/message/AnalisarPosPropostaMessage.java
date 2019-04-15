package br.com.camila.statemachine.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AnalisarPosPropostaMessage implements Serializable {

    private static final long serialVersionUID = -4457019729364986621L;

    private String cpf;

    private Long numeroProposta;
}
