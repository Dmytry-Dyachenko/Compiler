package javaclasses.compiler.impl.statemachine.statement.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.statemachine.variable.VariableStateMachine;

public class InitVariableParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final VariableStateMachine variableStateMachine = new VariableStateMachine();
        return variableStateMachine.execute(reader, context);
    }
}
