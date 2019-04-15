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
public class AtualizarInfosPessoaisMessage implements Serializable {

    private static final long serialVersionUID = -1674398233840443453L;

    private String cpf;

    private Long numeroProposta;
}