package javaclasses.compiler.impl.statemachine.calculatable;

public enum CalculableState {
    START,
    NUMBER,
    FUNCTION_CALL,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    EXPRESSION,
    FINISH
}
