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
public class CriarPropostaMessage implements Serializable {

    private static final long serialVersionUID = 3237658986214725680L;

    private final String cpf;

}
