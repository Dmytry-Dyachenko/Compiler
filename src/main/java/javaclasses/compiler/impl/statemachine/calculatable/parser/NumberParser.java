package javaclasses.compiler.impl.statemachine.calculatable.parser;

import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.EvaluationContext;
import javaclasses.compiler.impl.ExpressionParser;
import javaclasses.compiler.impl.ExpressionReader;
import javaclasses.compiler.impl.command.NumberCommand;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class NumberParser implements ExpressionParser {
    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CompilationException {
        final DecimalFormat format = new DecimalFormat("0.0");
        final ParsePosition parsePosition = new ParsePosition(0);

        final Number number = format.parse(reader.getRemainingExpression(), parsePosition);

        if (parsePosition.getErrorIndex() == -1) {

            final double doubleValue = number.doubleValue();
            context.pushCommandToContext(new NumberCommand());
            reader.incrementParsePosition(parsePosition.getIndex());

            return true;
        }

        return false;
    }
}
