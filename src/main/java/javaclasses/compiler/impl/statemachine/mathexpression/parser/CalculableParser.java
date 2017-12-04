package javaclasses.compiler.impl.statemachine.mathexpression.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.calculatable.CalculableImpl;

public class CalculableParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final CalculableImpl calculable = new CalculableImpl();
        return calculable.calculate(reader, context);
    }
}
