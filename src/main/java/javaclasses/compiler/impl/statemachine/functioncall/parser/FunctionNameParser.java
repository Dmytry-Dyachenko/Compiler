package javaclasses.compiler.impl.statemachine.functioncall.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.command.FunctionCallCommand;
import javaclasses.compiler.impl.statemachine.functioncall.function.FunctionFactory;

public class FunctionNameParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {

        final FunctionFactory factory = new FunctionFactory();
        final String expression = reader.getRemainingExpression();

        for (String name : factory.getFunctionsName()) {
            if (expression.startsWith(name)) {
                context.pushCommandToContext(new FunctionCallCommand(name));
                reader.incrementParsePosition(name.length());
                return true;
            }
        }
        return false;
    }
}
