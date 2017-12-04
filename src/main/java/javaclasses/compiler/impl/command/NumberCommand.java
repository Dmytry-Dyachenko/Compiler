package javaclasses.compiler.impl.command;

import javaclasses.compiler.impl.Command;
import javaclasses.compiler.impl.EvaluationContext;

public class NumberCommand extends Command {

    final double number;

    public NumberCommand(double number) {
        this.number = number;
    }

    @Override
    public void execute(EvaluationContext context) {
        context.pushNumberToOperandStack(this.number);
    }
}
