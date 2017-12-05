package javaclasses.compiler.impl.statemachine.functioncall.function;

import javaclasses.compiler.impl.Function;

import java.util.List;
import java.util.Optional;

public class PrintFunction implements Function {
    @Override
    public Optional<Double> execute(List<Double> arguments) {
        System.out.println(arguments.get(0));
        return Optional.empty();
    }

    @Override
    public int getMinCountOfArguments() {
        return 1;
    }

    @Override
    public int getMaxCountOfArguments() {
        return 1;
    }
}
