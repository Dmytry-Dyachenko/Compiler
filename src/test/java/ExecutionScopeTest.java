import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.statemachine.executionscope.ExecutionScope;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExecutionScopeTest {
    final private ExecutionScope executionScope = new ExecutionScope();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testVariableCreation() throws CompilationException {
        executionScope.compile("a=1;b=2");
    }

    @Test
    public void testFunctionPrint() throws CompilationException {
        executionScope.compile("print(1);");
        assertEquals("1.0\r\n", outContent.toString());

    }

}
