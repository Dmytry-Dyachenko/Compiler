package javaclasses.compiler.impl.statemachine.executionscope.parser;

import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;

public class EndOfExecutionScope implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {
        return reader.endOfExpression() || reader.getRemainingExpression().startsWith(";") || reader.getRemainingExpression().startsWith(")");
    }
}

