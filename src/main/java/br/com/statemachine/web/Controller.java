package br.com.statemachine.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.statemachine.service.IniciarPropostaService;

@RestController
public class Controller {

    @Autowired
    private IniciarPropostaService iniciarPropostaService;

    @PostMapping
    public void salvar(@RequestBody String cpf) {

        iniciarPropostaService.executar(cpf);
    }
}
