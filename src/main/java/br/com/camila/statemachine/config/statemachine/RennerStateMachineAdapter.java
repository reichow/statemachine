package br.com.camila.statemachine.config.statemachine;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.Tipo;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;

public abstract class RennerStateMachineAdapter extends StateMachineConfigurerAdapter<Estados, Eventos> {

    private final Tipo tipo;

    RennerStateMachineAdapter(Tipo tipo){
        this.tipo = tipo;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<Estados, Eventos> config) throws Exception {
        config.withConfiguration()
                .machineId(tipo.name());
    }

}
