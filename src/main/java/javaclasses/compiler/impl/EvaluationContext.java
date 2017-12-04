package javaclasses.compiler.impl;

import javaclasses.compiler.impl.statemachine.variable.VariableContext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class EvaluationContext implements OutputContext {

    private Deque<Command> commands = new ArrayDeque<>();
    private Deque<Double> operandStack = new ArrayDeque<>();
    // private Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    private Deque<VariableContext> variables = new ArrayDeque<>();


    @Override
    public Optional<Double> getResult() {
        commands.forEach(command -> command.execute(this));
        return Optional.ofNullable(operandStack.pop());
    }

    @Override
    public void pushCommandToContext(Command command) {
        commands.add(command);
    }

    public void pushNumberToOperandStack(double number) {
        operandStack.push(number);
    }

    public void createNewVariableWithName(String variableName) {
        if (!isVariableAlreadyExist(variableName)) {
            variables.add(new VariableContext(variableName));
        }
    }

    private boolean isVariableAlreadyExist(String variableName) {
        for (VariableContext variable : variables) {
            if (variable.getVariableName().equals(variableName)) {
                return true;
            }
        }
        return false;
    }
}
