package javaclasses.compiler.impl;

import javaclasses.compiler.impl.statemachine.variable.VariableContext;

import java.util.*;

public class EvaluationContext implements OutputContext {

    private Deque<Command> commands = new ArrayDeque<>();
    private Deque<Double> operandStack = new ArrayDeque<>();
    // private Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    private Deque<VariableContext> variables = new ArrayDeque<>();


    @Override
    public Optional<Double> getResult() {
        commands.forEach(command -> command.execute(this));
        commands.removeAll(commands);
        if (operandStack.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(operandStack.pop());
    }

    @Override
    public void pushCommandToContext(Command command) {
        commands.push(command);
    }

    public void pushNumberToOperandStack(double number) {
        operandStack.push(number);
    }

    public void createNewVariableWithNameAndValue(String variableName) {
        if (!isVariableAlreadyExist(variableName)) {
            variables.add(new VariableContext(variableName, operandStack.pop()));
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
