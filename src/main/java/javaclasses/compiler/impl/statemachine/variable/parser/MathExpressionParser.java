package javaclasses.compiler.impl.statemachine.variable.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.mathexpression.MathExpression;

public class MathExpressionParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final MathExpression mathExpression = new MathExpression();
        return mathExpression.calculate(reader, context);
    }
}
