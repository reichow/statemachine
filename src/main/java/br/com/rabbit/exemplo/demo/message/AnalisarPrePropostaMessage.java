package br.com.rabbit.exemplo.demo.message;

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
public class AnalisarPrePropostaMessage implements Serializable {

    private static final long serialVersionUID = -6343570608722751707L;

    private final String cpf;
}
