package javaclasses.compiler.impl.statemachine.statement.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.statemachine.statement.StatementState;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.compiler.impl.statemachine.statement.StatementState.*;

public class StatementParserFactory {
    private final Map<StatementState, ExpressionParser> parsers = new HashMap<StatementState, ExpressionParser>() {{
        put(INIT_VARIABLE, new InitVariableParser());
        put(FUNCTION_CALL, new FunctionCallParser());
        put(LOOP, new LoopParser());
        put(FINISH, new EndOfStatement());
    }};

    /**
     * Dependence of state
     *
     * @param state conditional for our next logic step.
     * @return state executor.
     */
    public ExpressionParser getParser(StatementState state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
