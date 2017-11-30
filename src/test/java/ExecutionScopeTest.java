import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.statemachine.executionscope.ExecutionScope;
import org.junit.Test;

public class ExecutionScopeTest {
    final private ExecutionScope executionScope = new ExecutionScope();

    @Test(expected = CompilationException.class)
    public void testEmptyString() throws CompilationException {
        executionScope.compile("");
    }

}
