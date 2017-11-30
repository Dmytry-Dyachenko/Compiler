package javaclasses.compiler.impl.statemachine.executionscope.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.statemachine.statement.StatementImpl;

public class StatementParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final StatementImpl statement = new StatementImpl();
        return statement.execute(reader);
    }
}
