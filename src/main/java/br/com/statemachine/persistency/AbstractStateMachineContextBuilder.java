package br.com.statemachine.persistency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.region.Region;
import org.springframework.statemachine.state.AbstractState;
import org.springframework.statemachine.state.HistoryPseudoState;
import org.springframework.statemachine.state.PseudoState;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.AbstractStateMachine;
import org.springframework.statemachine.support.DefaultExtendedState;
import org.springframework.statemachine.support.DefaultStateMachineContext;

public class AbstractStateMachineContextBuilder<Estados, Eventos> {

    protected StateMachineContext<Estados, Eventos> buildStateMachineContext(StateMachine<Estados, Eventos> stateMachine) {
        Estados id = null;

        ExtendedState extendedState = new DefaultExtendedState();
        extendedState.getVariables().putAll(stateMachine.getExtendedState().getVariables());

        ArrayList<StateMachineContext<Estados, Eventos>> childs = new ArrayList<>();
        State<Estados, Eventos> state = stateMachine.getState();

        if (state.isSubmachineState()) {
            id = getDeepState(state);

        } else if (state.isOrthogonal()) {
            Collection<Region<Estados, Eventos>> regions = ((AbstractState<Estados, Eventos>) state).getRegions();

            for (Region<Estados, Eventos> r : regions) {
                StateMachine<Estados, Eventos> rsm = (StateMachine<Estados, Eventos>) r;
                childs.add(buildStateMachineContext(rsm));
            }

            id = state.getId();

        } else {
            id = state.getId();
        }

        Map<Estados, Estados> historyStates = new HashMap<>();
        PseudoState<Estados, Eventos> historyState = ((AbstractStateMachine<Estados, Eventos>) stateMachine).getHistoryState();

        if (historyState != null) {
            historyStates.put(null, ((HistoryPseudoState<Estados, Eventos>) historyState).getState().getId());
        }

        Collection<State<Estados, Eventos>> states = stateMachine.getStates();

        for (State<Estados, Eventos> ss : states) {
            if (ss.isSubmachineState()) {
                StateMachine<Estados, Eventos> submachine = ((AbstractState<Estados, Eventos>) ss).getSubmachine();
                PseudoState<Estados, Eventos> ps = ((AbstractStateMachine<Estados, Eventos>) submachine).getHistoryState();

                if (ps != null) {
                    State<Estados, Eventos> pss = ((HistoryPseudoState<Estados, Eventos>) ps).getState();

                    if (pss != null) {
                        historyStates.put(ss.getId(), pss.getId());
                    }
                }
            }
        }

        return new DefaultStateMachineContext<Estados, Eventos>(childs, id, null, null, extendedState, historyStates,
            stateMachine.getId());
    }

    private Estados getDeepState(State<Estados, Eventos> state) {
        Collection<Estados> ids1 = state.getIds();

        Estados[] ids2 = (Estados[]) ids1.toArray();

        return ids2[ids2.length - 1];
    }
}
