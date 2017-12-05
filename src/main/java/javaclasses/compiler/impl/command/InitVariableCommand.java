package javaclasses.compiler.impl.command;

import javaclasses.compiler.impl.Command;
import javaclasses.compiler.impl.EvaluationContext;

public class InitVariableCommand extends Command {

    final String variableName;

    public InitVariableCommand(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public void execute(EvaluationContext context) {
        context.createNewVariableWithNameAndValue(this.variableName);
    }
}
