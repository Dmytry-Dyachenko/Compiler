package javaclasses.compiler.impl.statemachine.variable.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;

public class AssignParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {

        final String expression = reader.getRemainingExpression();
        final String ASSIGNMENT = "=";

        if (expression.startsWith(ASSIGNMENT)) {
            reader.incrementParsePosition(ASSIGNMENT.length());
            return true;
        }
        return false;
    }
}
