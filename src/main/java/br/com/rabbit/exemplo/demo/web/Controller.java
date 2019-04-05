package br.com.rabbit.exemplo.demo.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbit.exemplo.demo.message.CriarPropostaMessage;

//@RequestMapping("/proposta")
@RestController
public class Controller {

//    @Autowired
//    @EventTemplate
//    private RabbitTemplate eventTemplate;
//
//    @Autowired
//    private MessagingConfiguration messagingConfiguration;

    @PostMapping
    public void salvar(@RequestBody String cpf) {

        CriarPropostaMessage obj = CriarPropostaMessage.builder().cpf(cpf).build();

//        eventTemplate.convertAndSend(Messaging.CRIAR_PROPOSTA.getExchange(), Messaging.CRIAR_PROPOSTA.getRoutingKey(), obj);

//        eventTemplate.sendAndReceive(
//            Messaging.CRIAR_PROPOSTA.getExchange(),
//            Messaging.CRIAR_PROPOSTA.getRoutingKey(),
//            obj);

    }

}
