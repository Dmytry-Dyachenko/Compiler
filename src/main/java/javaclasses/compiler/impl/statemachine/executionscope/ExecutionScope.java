package javaclasses.compiler.impl.statemachine.executionscope;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.Compiler;
import javaclasses.compiler.FiniteStateMachine;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.context.EvaluationContext;
import javaclasses.compiler.impl.statemachine.executionscope.parser.ExecutionScopeParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.compiler.impl.statemachine.executionscope.ExecutionScopeState.*;


public class ExecutionScope extends FiniteStateMachine<
        EvaluationContext,
        ExpressionReader,
        ExecutionScopeState,
        CompilationException>
        implements Compiler {

    private final ExecutionScopeParserFactory parserFactory = new ExecutionScopeParserFactory();

    private final Map<ExecutionScopeState, Set<ExecutionScopeState>> transitions = new HashMap<ExecutionScopeState, Set<ExecutionScopeState>>() {{
        put(START, of(STATEMENT));
        put(STATEMENT, of(STATEMENT, FINISH));
    }};

    @Override
    public void compile(String executionScopes) throws CompilationException {
        ExpressionReader reader = new ExpressionReader(executionScopes);
        final EvaluationContext evaluationContext = new EvaluationContext();/*message -> {
            throw new CompilationException(message.replace(".", " ") + "at position " + reader.getParsePosition() + "!");
        });*/
        start(START, reader, evaluationContext);
    }

    @Override
    protected boolean acceptState(ExpressionReader reader,
                                  EvaluationContext context, ExecutionScopeState nextState) throws CompilationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(ExecutionScopeState state) {
        return state == FINISH;
    }

    @Override
    protected Set<ExecutionScopeState> getPossibleTransitions(ExecutionScopeState state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(ExecutionScopeState executionScopeState, ExpressionReader reader)
            throws CompilationException {

        throw new CompilationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
