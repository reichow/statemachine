package br.com.camila.statemachine.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.message.CriarPropostaMessage;
import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.message.AnalisarPosPropostaMessage;
import br.com.camila.statemachine.message.AnalisarPrePropostaMessage;
import br.com.camila.statemachine.message.AtualizarEmailValidadoMessage;
import br.com.camila.statemachine.message.AtualizarInfosPessoaisMessage;
import br.com.camila.statemachine.messaging.Messaging;
import br.com.camila.statemachine.service.CustomStateMachineService;
import br.com.camila.statemachine.service.IniciarPropostaService;

@RestController
public class Controller {

    @Autowired
    private IniciarPropostaService iniciarPropostaService;

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @Autowired
    private StateMachineFactory<Estados, Eventos> factory;

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    @PostMapping
    public void salvar(@RequestBody String cpf) {

        CriarPropostaMessage novaProposta = CriarPropostaMessage.builder().cpf(cpf).build();

        eventTemplate.convertAndSend(
            Messaging.CRIAR_PROPOSTA.getExchange(),
            Messaging.CRIAR_PROPOSTA.getRoutingKey(),
            novaProposta);
    }

    @PostMapping
    public void preAnalise(@RequestBody AnalisarPrePropostaMessage message) {

        eventTemplate.convertAndSend(
            Messaging.ANALISAR_PRE_PROPOSTA.getExchange(),
            Messaging.ANALISAR_PRE_PROPOSTA.getRoutingKey(),
            message);
    }

    @PostMapping
    public void atualizarInfosPessoais(@RequestBody AtualizarInfosPessoaisMessage message) {

        eventTemplate.convertAndSend(
            Messaging.ATUALIZAR_INFOS_PESSOAIS.getExchange(),
            Messaging.ATUALIZAR_INFOS_PESSOAIS.getRoutingKey(),
            message);
    }

    @PostMapping
    public void atualizarEmailValidado(@RequestBody AtualizarEmailValidadoMessage message) {

        eventTemplate.convertAndSend(
            Messaging.ATUALIZAR_EMAIL_VALIDADO.getExchange(),
            Messaging.ATUALIZAR_EMAIL_VALIDADO.getRoutingKey(),
            message);
    }

    @PostMapping
    public void analisarPosProposta(@RequestBody AnalisarPosPropostaMessage message) {

        eventTemplate.convertAndSend(
            Messaging.ANALISAR_POS_PROPOSTA.getExchange(),
            Messaging.ANALISAR_POS_PROPOSTA.getRoutingKey(),
            message);
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
