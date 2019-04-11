package br.com.statemachine.service.proposta;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.statemachine.domain.Eventos;
import br.com.statemachine.entity.Proposta;
import br.com.statemachine.repository.PropostaRepository;
import br.com.statemachine.service.CustomStateMachineService;

@Service
public class CriarPropostaService {

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @Autowired
    private PropostaRepository repository;

    public Proposta executar(String cpf) {

        Long numeroProposta = gerarNumeroProposta();


        // salvar proposta no banco
        Proposta proposta = Proposta.builder()
            .estado(customStateMachineService.getState(numeroProposta))
            .numero(numeroProposta)
            .cpf(cpf).build();

        iniciarMaquinaDeEstados(proposta);

        Proposta propostaSalva = repository.save(proposta);

        return propostaSalva;
    }

    private Long gerarNumeroProposta() {
        Proposta ultimaProposta = repository.findTopByOrderByNumeroDesc().orElse(Proposta.builder().numero(0L).build());

        return ultimaProposta.getNumero() + 1L;
    }

    private void iniciarMaquinaDeEstados(Proposta proposta) {

        customStateMachineService.start(proposta.getNumero());

        // setar variaveis na maquina de estado;
        Map<String, Object> map = new HashMap<>();
        map.put("cpf", proposta.getCpf());
        map.put("numeroProposta", proposta.getNumero());

        customStateMachineService.setVariables(proposta.getNumero(), map);

        customStateMachineService.sendEvent(proposta.getNumero(), Eventos.INICIAR);
    }
}
