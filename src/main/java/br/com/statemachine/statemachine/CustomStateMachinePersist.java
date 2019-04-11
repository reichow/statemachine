package br.com.statemachine.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.kryo.KryoStateMachineSerialisationService;
import org.springframework.statemachine.service.StateMachineSerialisationService;
import org.springframework.stereotype.Component;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.entity.StateMachineEntity;
import br.com.statemachine.repository.StateMachineRepository;

@Component
public class CustomStateMachinePersist implements StateMachinePersist<Estados, Eventos, String> {

    @Autowired
    private StateMachineRepository stateMachineRepository;

    private final StateMachineSerialisationService<Estados, Eventos> serialisationService;

    public CustomStateMachinePersist() {
        this.serialisationService = new KryoStateMachineSerialisationService<>();
    }

    @Override
    public void write(StateMachineContext<Estados, Eventos> context, String id) throws Exception {

        StateMachineEntity entity = stateMachineRepository.findByIdMaquina(id).orElse(StateMachineEntity.builder().idMaquina(id).build());

        entity.setEstado(context.getState().name());
        entity.setContexto(serialisationService.serialiseStateMachineContext(context));

        stateMachineRepository.save(entity);
    }

    @Override
    public StateMachineContext<Estados, Eventos> read(String id) throws Exception {
        StateMachineEntity entity = stateMachineRepository.findByIdMaquina(id).orElse(null);

        if (entity != null) {
            return serialisationService.deserialiseStateMachineContext(entity.getContexto());
        }

        return null;
    }

}
