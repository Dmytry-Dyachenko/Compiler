package javaclasses.compiler.impl.statemachine.variable;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.FiniteStateMachine;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.variable.parser.VariableParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.compiler.impl.statemachine.variable.VariableState.*;

public class VariableStateMachine extends FiniteStateMachine<
        EvaluationContext,
        ExpressionReader,
        VariableState,
        CompilationException> {

    private final VariableParserFactory parserFactory = new VariableParserFactory();

    private final Map<VariableState, Set<VariableState>> transitions = new HashMap<VariableState, Set<VariableState>>() {{
        put(START, of(VARIABLE_NAME));
        put(VARIABLE_NAME, of(ASSIGN));
        put(ASSIGN, of(EXPRESSION));
        put(EXPRESSION, of(FINISH));
    }};

    public boolean execute(ExpressionReader reader, EvaluationContext evaluationContext) {
        /*message -> {
            throw new CompilationException(message.replace(".", " ") + "at position " + reader.getParsePosition() + "!");
        });*/
        try {
            start(START, reader, evaluationContext);
        } catch (CompilationException e) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean acceptState(ExpressionReader reader,
                                  EvaluationContext context, VariableState nextState) throws CompilationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(VariableState state) {
        return state == FINISH;
    }

    @Override
    protected Set<VariableState> getPossibleTransitions(VariableState state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(VariableState executionScopeState, ExpressionReader reader)
            throws CompilationException {

        throw new CompilationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
