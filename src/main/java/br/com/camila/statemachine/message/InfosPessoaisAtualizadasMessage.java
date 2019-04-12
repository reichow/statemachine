package br.com.camila.statemachine.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfosPessoaisAtualizadasMessage  implements Serializable {

    private static final long serialVersionUID = 693886396004667503L;

    private final Long numeroProposta;
}
