package javaclasses.compiler.impl.statemachine.variable;

public class VariableContext {
    private String variableName;
    private double variableValue;

    public VariableContext(String variableName) {
        this.variableName = variableName;
    }

    public double getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(double value) {
        this.variableValue = value;
    }

    public String getVariableName() {
        return variableName;
    }
}
