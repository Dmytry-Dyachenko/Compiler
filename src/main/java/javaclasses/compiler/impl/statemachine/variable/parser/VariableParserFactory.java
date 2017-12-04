package javaclasses.compiler.impl.statemachine.variable.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.statemachine.executionscope.parser.EndOfExecutionScope;
import javaclasses.compiler.impl.statemachine.statement.parser.EndOfStatement;
import javaclasses.compiler.impl.statemachine.variable.VariableState;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.compiler.impl.statemachine.variable.VariableState.*;


public class VariableParserFactory {
    private final Map<VariableState, ExpressionParser> parsers = new HashMap<VariableState, ExpressionParser>() {{
        put(VARIABLE_NAME, new VariableNameParser());
        put(ASSIGN, new AssignParser());
        put(EXPRESSION, new MathExpressionParser());
        put(FINISH, new EndOfExecutionScope());
    }};

    /**
     * Dependence of state
     *
     * @param state conditional for our next logic step.
     * @return state executor.
     */
    public ExpressionParser getParser(VariableState state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
