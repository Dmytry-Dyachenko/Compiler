package javaclasses.compiler.impl.statemachine.mathexpression.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.statemachine.executionscope.parser.EndOfExecutionScope;
import javaclasses.compiler.impl.statemachine.mathexpression.MathExpressionState;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.compiler.impl.statemachine.mathexpression.MathExpressionState.*;

public class MathExpressionParserFactory {
    private final Map<MathExpressionState, ExpressionParser> parsers = new HashMap<MathExpressionState, ExpressionParser>() {{
        put(CALCULABLE, new CalculableParser());
        put(BINARY_OPERATOR, new BinaryOperatorParser());
        put(FINISH, new EndOfExecutionScope());
    }};

    /**
     * Dependence of state
     *
     * @param state conditional for our next logic step.
     * @return state executor.
     */
    public ExpressionParser getParser(MathExpressionState state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
