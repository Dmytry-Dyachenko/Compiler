package javaclasses.compiler.impl.statemachine.executionscope.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.statemachine.executionscope.ExecutionScopeState;

import java.util.HashMap;
import java.util.Map;

import static javaclasses.compiler.impl.statemachine.executionscope.ExecutionScopeState.FINISH;
import static javaclasses.compiler.impl.statemachine.executionscope.ExecutionScopeState.STATEMENT;


public class ExecutionScopeParserFactory {

    private final Map<ExecutionScopeState, ExpressionParser> parsers = new HashMap<ExecutionScopeState, ExpressionParser>() {{
        put(STATEMENT, new StatementParser());
        put(FINISH, new EndOfExecutionScope());
    }};

    /**
     * Dependence of state
     *
     * @param state conditional for our next logic step.
     * @return state executor.
     */
    public ExpressionParser getParser(ExecutionScopeState state) {

        if (!parsers.containsKey(state)) {
            throw new IllegalStateException("Parser not found for state: " + state);
        }

        return parsers.get(state);
    }
}
