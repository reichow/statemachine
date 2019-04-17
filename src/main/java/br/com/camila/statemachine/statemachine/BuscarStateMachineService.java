package br.com.camila.statemachine.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;

@Service
public class BuscarStateMachineService {

    @Autowired
    private CustomStateMachineFactory factory;


    public StateMachine<Estados, Eventos> executar(final TipoProposta proposta) {

        if (proposta.name().equals(TipoProposta.CONTRATACAO_CCR.name())) {
            return factory.contratacaoCCR.getStateMachine(proposta.name());
        }

        if (proposta.name().equals(TipoProposta.CONTRATACAO_MC.name())) {
            return factory.contratacaoMC.getStateMachine(proposta.name());
        }

        return null;
    }
}
