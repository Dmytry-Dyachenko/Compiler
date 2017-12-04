package javaclasses.compiler.impl;


import java.util.Optional;

public interface OutputContext {

    Optional<Double> getResult();

    void pushCommandToContext(Command command);
}
