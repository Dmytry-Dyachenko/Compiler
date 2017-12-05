package javaclasses.compiler.impl.statemachine.statement.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.functioncall.FunctionCall;

public class FunctionCallParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final FunctionCall functionCall = new FunctionCall();
        return functionCall.execute(reader, context);
    }
}
