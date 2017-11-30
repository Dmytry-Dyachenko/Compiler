package javaclasses.compiler.impl.statemachine.functioncall;

public enum FunctionCallState {
    START,
    FUNCTION_NAME,
    OPEN_BRACKET,
    EXPRESSION,
    ARGUMENT_DELIMITER,
    CLOSE_BRACKET,
    FINISH
}
