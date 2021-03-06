package javaclasses.compiler.impl;

import javaclasses.compiler.CompilationException;

public interface ExpressionParser {

    boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException;

}
