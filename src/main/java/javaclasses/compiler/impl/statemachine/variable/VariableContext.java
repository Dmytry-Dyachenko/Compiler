package javaclasses.compiler.impl.statemachine.variable;

public class VariableContext {
    final private String variableName;
    final private double variableValue;

    public VariableContext(String variableName, double variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    public double getVariableValue() {
        return variableValue;
    }

    public String getVariableName() {
        return variableName;
    }
}
