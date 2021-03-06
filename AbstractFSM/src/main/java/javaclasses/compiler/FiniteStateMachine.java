package javaclasses.compiler;

import java.util.Optional;
import java.util.Set;

public abstract class FiniteStateMachine<
        Output,
        Input,
        State extends Enum,
        TransitionError extends Exception> {

    public void start(State startState, Input input, Output output) throws TransitionError {

        State currentState = startState;

        while (!isFinishState(currentState)) {

            final Optional<State> nextState = moveForward(input, output, currentState);

            if (nextState.isPresent()) {
                currentState = nextState.get();
            } else {
                raiseDeadlockError(currentState, input);
            }
        }
    }

    private Optional<State> moveForward(Input input, Output output, State currentState) throws TransitionError {

        final Set<State> transitions = getPossibleTransitions(currentState);

        for (State possibleState : transitions) {
            if (acceptState(input, output, possibleState)) {
                return Optional.of(possibleState);
            }
        }

        return Optional.empty();
    }

    protected abstract boolean acceptState(Input input, Output output, State nextState) throws TransitionError;

    protected abstract boolean isFinishState(State currentState);

    protected abstract Set<State> getPossibleTransitions(State currentState);

    protected abstract void raiseDeadlockError(State state, Input input)
            throws TransitionError;


}
