package br.com.camila.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.entity.Proposta;
import br.com.camila.statemachine.repository.PropostaRepository;

@Service
public class BuscarPropostaService {

    @Autowired
    private PropostaRepository repository;

    public Proposta buscarPorId(Long id) {

        return repository.findById(id).orElse(null);
    }
}
