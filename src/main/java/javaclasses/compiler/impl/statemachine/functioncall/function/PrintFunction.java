package javaclasses.compiler.impl.statemachine.functioncall.function;

import javaclasses.compiler.impl.Function;

import java.util.List;

public class PrintFunction implements Function {
    @Override
    public double execute(List<Double> arguments) {
        System.out.println(arguments.get(0));
        return 0;
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
