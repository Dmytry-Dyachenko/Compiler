package javaclasses.compiler.impl;

import java.util.List;
import java.util.Optional;

public interface Function {
    Optional<Double> execute(List<Double> arguments);

    int getMinCountOfArguments();

    int getMaxCountOfArguments();
}
