package br.com.statemachine.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.service.CustomStateMachineService;
import br.com.statemachine.service.proposta.IniciarPropostaService;

@RestController
public class Controller {

    @Autowired
    private IniciarPropostaService iniciarPropostaService;

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @Autowired
    private StateMachineFactory<Estados, Eventos> factory;

    @PostMapping
    public void salvar(@RequestBody String cpf) {

        iniciarPropostaService.executar(cpf);
    }

    @GetMapping
    public String buscarMaquinaDeEstados() {

        StateMachine<Estados, Eventos> stateMachine = factory.getStateMachine();

        final String teste = customStateMachineService.getState(6L);

        customStateMachineService.sendEvent(6L, Eventos.ANALISAR);

        State<Estados, Eventos> novoEstado = stateMachine.getState();

        return teste;
    }
}
