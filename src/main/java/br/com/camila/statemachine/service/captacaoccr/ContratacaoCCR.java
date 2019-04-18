package br.com.camila.statemachine.service.captacaoccr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContratacaoCCR {

    @Autowired
    private AnalisarPrePropostaService analisarPrePropostaService;
}
