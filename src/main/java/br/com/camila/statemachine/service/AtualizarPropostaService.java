package br.com.camila.statemachine.service;

import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.entity.Proposta;
import br.com.camila.statemachine.repository.PropostaRepository;

@Service
public class AtualizarPropostaService {

    @Autowired
    private PropostaRepository repository;

    public void executar(final Long numeroProposta, final Estados id) {

        Proposta proposta = repository
            .findByNumero(numeroProposta)
            .orElse(null);

        if (nonNull(proposta)) {
            proposta.setEstado(id.name());
            repository.save(proposta);
        }
    }
}
