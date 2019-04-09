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
public class CriarPropostaMessage implements Serializable {

    private static final long serialVersionUID = 3237658986214725680L;

    private String cpf;

}
