package javaclasses.compiler.impl.command;

import javaclasses.compiler.impl.Command;
import javaclasses.compiler.impl.EvaluationContext;

public class FunctionCallCommand extends Command {

    final private String functionName;

    public FunctionCallCommand(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public void execute(EvaluationContext context) {

    }
}
