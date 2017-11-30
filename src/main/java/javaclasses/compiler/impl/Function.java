package javaclasses.compiler.impl;

import java.util.List;

public interface Function {
    double execute(List<Double> arguments);

    int getMinCountOfArguments();

    int getMaxCountOfArguments();
}
