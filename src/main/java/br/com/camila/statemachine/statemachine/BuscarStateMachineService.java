package br.com.camila.statemachine.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;

@Service
public class BuscarStateMachineService {

    @Autowired
    @Qualifier("CONTRATACAO_CCR")
    private StateMachineFactory<Estados, Eventos> contratacaoCCR;

    @Autowired
    @Qualifier("CONTRATACAO_MC")
    private StateMachineFactory<Estados, Eventos> contratacaoMC;

    @Autowired
    @Qualifier("TESTE")
    private StateMachineFactory<Estados, Eventos> teste;

    public StateMachine<Estados, Eventos> executar(final TipoProposta proposta) {

        if (proposta.name().equals(TipoProposta.CONTRATACAO_CCR.name())) {
            return contratacaoCCR.getStateMachine(proposta.name());
        }

        if (proposta.name().equals(TipoProposta.CONTRATACAO_MC.name())) {
            return contratacaoMC.getStateMachine(proposta.name());
        }

        if (proposta.name().equals(TipoProposta.TESTE.name())) {
            return teste.getStateMachine(proposta.name());
        }

        return null;
    }
}
