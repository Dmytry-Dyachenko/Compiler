package javaclasses.compiler.impl.statemachine.functioncall.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.statemachine.calculatable.parser.CloseBracketParser;
import javaclasses.compiler.impl.statemachine.calculatable.parser.OpenBracketParser;
import javaclasses.compiler.impl.statemachine.executionscope.parser.EndOfExecutionScope;
import javaclasses.compiler.impl.statemachine.functioncall.FunctionCallState;
import javaclasses.compiler.impl.statemachine.variable.parser.MathExpressionParser;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.compiler.impl.statemachine.functioncall.FunctionCallState.*;

public class FunctionCallParserFactory {
    private final Map<FunctionCallState, ExpressionParser> parsers = new HashMap<FunctionCallState, ExpressionParser>() {{
        put(FUNCTION_NAME, new FunctionNameParser());
        put(OPEN_BRACKET, new OpenBracketParser());
        put(CLOSE_BRACKET, new CloseBracketParser());
        put(EXPRESSION, new MathExpressionParser());
        put(ARGUMENT_DELIMITER, new ArgumentDelimiterParser());
        put(FINISH, new EndOfExecutionScope());
    }};

    /**
     * Dependence of state
     *
     * @param state conditional for our next logic step.
     * @return state executor.
     */
    public ExpressionParser getParser(FunctionCallState state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
