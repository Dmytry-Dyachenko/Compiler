package javaclasses.compiler.impl.command;

import javaclasses.compiler.impl.Command;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.Function;

public class FunctionCallCommand extends Command {

    final private Function function;

    public FunctionCallCommand(Function function) {
        this.function = function;
    }

    @Override
    public void execute(EvaluationContext context) {
        function.execute(context.getFunctionArguments());
    }
}
