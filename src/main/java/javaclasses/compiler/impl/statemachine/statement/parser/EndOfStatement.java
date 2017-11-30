package javaclasses.compiler.impl.statemachine.statement.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.OutputContext;

public class EndOfStatement implements ExpressionParser {
    private static final String STATEMENT_DELIMITER = ";";

    @Override
    public boolean parse(ExpressionReader reader, OutputContext context)  {
        return reader.getRemainingExpression().startsWith(STATEMENT_DELIMITER);
    }
}
