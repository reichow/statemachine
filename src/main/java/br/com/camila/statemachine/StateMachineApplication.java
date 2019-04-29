package br.com.camila.statemachine;

import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.Tipo;
import br.com.camila.statemachine.statemachine.CustomStateMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import java.util.Objects;

import static java.util.Optional.ofNullable;

@SpringBootApplication
@Slf4j
public class StateMachineApplication implements CommandLineRunner {

	@Autowired
	private CustomStateMachineService service;

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		StateMachine s = service.getStateMachine("1", Tipo.TESTE);
		printState(s);
		service.start(1l, Tipo.TESTE);

		StateMachine s2 = service.getStateMachine("1", Tipo.TESTE);

		sendEvent(Eventos.ANALISAR);

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
		service.sendEvent(1l, event, Tipo.TESTE);
	}
}
