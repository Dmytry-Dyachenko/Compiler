package javaclasses.compiler.impl.statemachine.variable.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;

public class MathExpressionParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        return false;
    }
}
