package br.com.statemachine.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AnalisarPrePropostaMessage implements Serializable {

    private static final long serialVersionUID = -6343570608722751707L;

    private String cpf;
}
