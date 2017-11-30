package javaclasses.compiler.impl;

import java.util.ArrayList;
import java.util.List;

public class EvaluationContext implements OutputContext {

    private List<Command> commands = new ArrayList<>();

    @Override
    public double getResult() {
        return 0;
    }

    @Override
    public void pushCommandToContext(Command command) {
        commands.add(command);
    }
}
