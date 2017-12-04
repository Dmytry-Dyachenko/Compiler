package javaclasses.compiler.impl.statemachine.variable.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.command.InitVariableCommand;
import javaclasses.compiler.impl.statemachine.functioncall.function.FunctionFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class VariableNameParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final String expression = reader.getRemainingExpression();

        if (isItFunction(expression)) {
            return false;
        }

        final Pattern pattern = compile("[a-z]+");
        final Matcher matcher = pattern.matcher(expression);

        if (matcher.find()) {
            String variableName = matcher.group(0);
            context.pushCommandToContext(new InitVariableCommand(variableName));
            reader.incrementParsePosition(variableName.length());
            return true;
        }
        return false;
    }

    private boolean isItFunction(String expression) {
        final FunctionFactory factory = new FunctionFactory();
        for (String functionName : factory.getFunctionsName()) {
            if (expression.startsWith(functionName)) {
                return true;
            }
        }
        return false;
    }
}
