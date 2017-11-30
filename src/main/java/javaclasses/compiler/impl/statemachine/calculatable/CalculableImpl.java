package javaclasses.compiler.impl.statemachine.calculatable;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.FiniteStateMachine;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.calculatable.parser.CalculableParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.compiler.impl.statemachine.calculatable.CalculableState.*;

public class CalculableImpl extends FiniteStateMachine<
        EvaluationContext,
        ExpressionReader,
        CalculableState,
        CompilationException> {

    private final CalculableParserFactory parserFactory = new CalculableParserFactory();

    private final Map<CalculableState, Set<CalculableState>> transitions = new HashMap<CalculableState, Set<CalculableState>>() {{
        put(START, of(NUMBER, FUNCTION_CALL, OPEN_BRACKET));
        put(NUMBER, of(FINISH));
        put(FUNCTION_CALL, of(FINISH));
        put(OPEN_BRACKET, of(EXPRESSION));
        put(EXPRESSION, of(CLOSE_BRACKET));
        put(CLOSE_BRACKET, of(FINISH));
    }};

    public boolean calculate(ExpressionReader reader) {
        final EvaluationContext evaluationContext = new EvaluationContext();/*message -> {
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
                                  EvaluationContext context, CalculableState nextState) throws CompilationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(CalculableState state) {
        return state == FINISH;
    }

    @Override
    protected Set<CalculableState> getPossibleTransitions(CalculableState state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(CalculableState executionScopeState, ExpressionReader reader)
            throws CompilationException {

        throw new CompilationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
