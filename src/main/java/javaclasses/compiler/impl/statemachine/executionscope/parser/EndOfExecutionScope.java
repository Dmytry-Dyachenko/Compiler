package javaclasses.compiler.impl.statemachine.executionscope.parser;

import javaclasses.compiler.impl.OutputContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;

public class EndOfExecutionScope implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, OutputContext context) {
        return reader.endOfExpression();
    }
}

