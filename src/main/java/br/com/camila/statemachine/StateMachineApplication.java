package br.com.camila.statemachine;

import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;
import br.com.camila.statemachine.statemachine.CustomStateMachineService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import java.util.Objects;
import java.util.Random;

import static java.util.Optional.ofNullable;

@SpringBootApplication
@Slf4j
public class StateMachineApplication implements CommandLineRunner {

	@Autowired
	private CustomStateMachineService service;

	Long proposta = Long.valueOf(RandomStringUtils.randomNumeric(5));

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {



		StateMachine s = service.getStateMachine(proposta.toString(), TipoProposta.TESTE);
		printState(s);
		service.start(proposta, TipoProposta.TESTE);

		service.getStateMachine(proposta.toString(), TipoProposta.TESTE);

		sendEvent(Eventos.ANALISAR);

		printState(s);

		sendEvent(Eventos.APROVAR);

		printState(s);

		sendEvent(Eventos.ANALISAR);

		printState(s);


		sendEvent(Eventos.APROVAR);

		printState(s);
	}

	private void printState(final StateMachine s){
		log.info(ofNullable(s).map(StateMachine::getState).map(Objects::toString).orElse("Not Init"));
	}

	private void sendEvent(final Eventos event){
		service.sendEvent(proposta, event, TipoProposta.TESTE);
	}
}
