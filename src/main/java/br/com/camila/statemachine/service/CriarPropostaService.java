package br.com.camila.statemachine.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.entity.Proposta;
import br.com.camila.statemachine.repository.PropostaRepository;

@Service
public class CriarPropostaService {

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @Autowired
    private PropostaRepository repository;

    public Proposta executar(String cpf) {

        Long numeroProposta = gerarNumeroProposta();

        Proposta proposta = Proposta.builder()
            .estado(customStateMachineService.getState(numeroProposta))
            .numero(numeroProposta)
            .cpf(cpf).build();

        iniciarMaquinaDeEstados(proposta);

        return repository.save(proposta);
    }

    private Long gerarNumeroProposta() {
        Proposta ultimaProposta = repository.findTopByOrderByNumeroDesc().orElse(Proposta.builder().numero(0L).build());

        return ultimaProposta.getNumero() + 1L;
    }

    private void iniciarMaquinaDeEstados(Proposta proposta) {

        customStateMachineService.start(proposta.getNumero());

        Map<String, Object> map = new HashMap<>();
        map.put("cpf", proposta.getCpf());
        map.put("numeroProposta", proposta.getNumero());

        customStateMachineService.setVariables(proposta.getNumero(), map);

        customStateMachineService.sendEvent(proposta.getNumero(), Eventos.INICIAR);
    }
}
