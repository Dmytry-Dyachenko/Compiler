package javaclasses.compiler.impl.statemachine.executionscope.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.EvaluationContext;

public class EndOfExecutionScope implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {
        return reader.endOfExpression() || reader.getRemainingExpression().startsWith(";");
    }
}

