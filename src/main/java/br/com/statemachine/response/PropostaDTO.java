package br.com.statemachine.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropostaDTO implements Serializable {

    private static final long serialVersionUID = 9218148834316875770L;

    private final String estado;

    private final String prop1;

    private final String prop2;

}
