package javaclasses.compiler.impl.statemachine.variable;

public enum VariableState {
    START,
    VARIABLE_NAME,
    ASSIGN,
    EXPRESSION,
    FINISH
}
