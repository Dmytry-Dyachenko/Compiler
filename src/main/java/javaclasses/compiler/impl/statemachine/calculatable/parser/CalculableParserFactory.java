package javaclasses.compiler.impl.statemachine.calculatable.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.statemachine.calculatable.CalculableState;
import javaclasses.compiler.impl.statemachine.executionscope.parser.EndOfExecutionScope;
import javaclasses.compiler.impl.statemachine.statement.parser.FunctionCallParser;
import javaclasses.compiler.impl.statemachine.variable.parser.MathExpressionParser;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.compiler.impl.statemachine.calculatable.CalculableState.*;


public class CalculableParserFactory {

    private final Map<CalculableState, ExpressionParser> parsers = new HashMap<CalculableState, ExpressionParser>() {{
        put(NUMBER, new NumberParser());
        put(FUNCTION_CALL, new FunctionCallParser());
        put(OPEN_BRACKET, new OpenBracketParser());
        put(CLOSE_BRACKET, new CloseBracketParser());
        put(EXPRESSION, new MathExpressionParser());
        put(FINISH, new EndOfExecutionScope());
    }};

    /**
     * Dependence of state
     *
     * @param state conditional for our next logic step.
     * @return state executor.
     */
    public ExpressionParser getParser(CalculableState state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
