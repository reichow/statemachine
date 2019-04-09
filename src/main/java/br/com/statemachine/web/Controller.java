package br.com.statemachine.web;

import br.com.statemachine.service.CriarPropostaService;
import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private CriarPropostaService criarPropostaService;

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

    @PostMapping
    public void salvar(@RequestBody String cpf) {

        stateMachine.start();
        stateMachine.getExtendedState().getVariables().put("cpf", cpf);
//        stateMachine.sendEvent(Eventos.INICIAR);

        criarPropostaService.executar(cpf);
    }
}
