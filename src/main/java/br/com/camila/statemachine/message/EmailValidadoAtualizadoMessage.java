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
public class EmailValidadoAtualizadoMessage implements Serializable {

    private static final long serialVersionUID = -1996829531072794479L;

    private String cpf;

    private Long numeroProposta;
}