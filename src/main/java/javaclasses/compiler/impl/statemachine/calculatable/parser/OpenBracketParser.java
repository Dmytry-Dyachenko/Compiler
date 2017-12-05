package javaclasses.compiler.impl.statemachine.calculatable.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;

public class OpenBracketParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {

        final String OPEN_BRACKET = "(";

        final String expression = reader.getRemainingExpression();
        if (expression.startsWith(OPEN_BRACKET)) {
            reader.incrementParsePosition(OPEN_BRACKET.length());
            return true;
        }
        return false;
    }
}
