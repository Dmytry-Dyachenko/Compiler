package javaclasses.compiler.impl.statemachine.functioncall;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.FiniteStateMachine;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.functioncall.parser.FunctionCallParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.compiler.impl.statemachine.functioncall.FunctionCallState.*;

public class FunctionCall extends FiniteStateMachine<
        EvaluationContext,
        ExpressionReader,
        FunctionCallState,
        CompilationException> {

    private final FunctionCallParserFactory parserFactory = new FunctionCallParserFactory();

    private final Map<FunctionCallState, Set<FunctionCallState>> transitions = new HashMap<FunctionCallState, Set<FunctionCallState>>() {{
        put(START, of(FUNCTION_NAME));
        put(FUNCTION_NAME, of(OPEN_BRACKET));
        put(OPEN_BRACKET, of(EXPRESSION, CLOSE_BRACKET));
        put(EXPRESSION, of(ARGUMENT_DELIMITER, CLOSE_BRACKET));
        put(ARGUMENT_DELIMITER, of(EXPRESSION));
        put(CLOSE_BRACKET, of(FINISH));
    }};

    public boolean execute(ExpressionReader reader) {
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
                                  EvaluationContext context, FunctionCallState nextState) throws CompilationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(FunctionCallState state) {
        return state == FINISH;
    }

    @Override
    protected Set<FunctionCallState> getPossibleTransitions(FunctionCallState state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(FunctionCallState executionScopeState, ExpressionReader reader)
            throws CompilationException {

        throw new CompilationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
