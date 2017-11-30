package javaclasses.compiler.impl.statemachine.statement.parser;

import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.EvaluationContext;

public class EndOfStatement implements ExpressionParser {
    private static final String STATEMENT_DELIMITER = ";";

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {
        return reader.getRemainingExpression().startsWith(STATEMENT_DELIMITER);
    }
}
