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
public class InfosPessoaisAtualizadasMessage implements Serializable {

    private static final long serialVersionUID = -2362599786344216399L;

    private String cpf;

    private Long numeroProposta;
}