package javaclasses.compiler.impl.statemachine.mathexpression;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.FiniteStateMachine;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.mathexpression.parser.MathExpressionParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.compiler.impl.statemachine.mathexpression.MathExpressionState.*;

public class MathExpression extends FiniteStateMachine<
        EvaluationContext,
        ExpressionReader,
        MathExpressionState,
        CompilationException> {

    private final MathExpressionParserFactory parserFactory = new MathExpressionParserFactory();

    private final Map<MathExpressionState, Set<MathExpressionState>> transitions = new HashMap<MathExpressionState, Set<MathExpressionState>>() {{
        put(START, of(CALCULABLE));
        put(CALCULABLE, of(BINARY_OPERATOR, FINISH));
        put(BINARY_OPERATOR, of(CALCULABLE));
    }};

    public boolean calculate(ExpressionReader reader, EvaluationContext evaluationContext) {
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
                                  EvaluationContext context, MathExpressionState nextState) throws CompilationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(MathExpressionState state) {
        return state == FINISH;
    }

    @Override
    protected Set<MathExpressionState> getPossibleTransitions(MathExpressionState state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(MathExpressionState executionScopeState, ExpressionReader reader)
            throws CompilationException {

        throw new CompilationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
