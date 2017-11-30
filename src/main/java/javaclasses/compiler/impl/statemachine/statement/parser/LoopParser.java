package javaclasses.compiler.impl.statemachine.statement.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.EvaluationContext;

public class LoopParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        return false;
    }
}
