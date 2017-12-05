package javaclasses.compiler.impl.statemachine.statement;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.FiniteStateMachine;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.statemachine.statement.parser.StatementParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.compiler.impl.statemachine.statement.StatementState.*;

public class StatementImpl extends FiniteStateMachine<
        EvaluationContext,
        ExpressionReader,
        StatementState,
        CompilationException> {

    private final StatementParserFactory parserFactory = new StatementParserFactory();

    private final Map<StatementState, Set<StatementState>> transitions = new HashMap<StatementState, Set<StatementState>>() {{
        put(START, of(INIT_VARIABLE, FUNCTION_CALL, LOOP));
        put(INIT_VARIABLE, of(FINISH));
        put(FUNCTION_CALL, of(FINISH));
        put(LOOP, of(FINISH));
    }};

    public boolean execute(ExpressionReader reader, EvaluationContext evaluationContext) {
        /*message -> {
            throw new CompilationException(message.replace(".", " ") + "at position " + reader.getParsePosition() + "!");
        });*/
        try {
            start(START, reader, evaluationContext);
            evaluationContext.getResult();
        } catch (CompilationException e) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean acceptState(ExpressionReader reader,
                                  EvaluationContext context, StatementState nextState) throws CompilationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(StatementState state) {
        return state == FINISH;
    }

    @Override
    protected Set<StatementState> getPossibleTransitions(StatementState state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(StatementState executionScopeState, ExpressionReader reader)
            throws CompilationException {

        throw new CompilationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
