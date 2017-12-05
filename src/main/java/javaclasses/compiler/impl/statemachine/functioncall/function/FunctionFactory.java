package javaclasses.compiler.impl.statemachine.functioncall.function;


import javaclasses.compiler.impl.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Router connecting function and their executing.
 */


public class FunctionFactory {

    private final Map<String, Function> functions = new HashMap<String, Function>() {{
        put("print", new PrintFunction());
    }};

    public Function getFunction(String name) {

        if (!functions.containsKey(name)) {
            throw new IllegalStateException("Function with name :" + name + " not found.");
        }

        return functions.get(name);
    }

    public Set<String> getFunctionsName() {
        return functions.keySet();
    }
}
