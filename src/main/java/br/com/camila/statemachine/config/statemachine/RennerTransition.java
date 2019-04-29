package br.com.camila.statemachine.config.statemachine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RennerTransition <E, V>{

    private final E source;
    private final E target;
    private final V event;
}
