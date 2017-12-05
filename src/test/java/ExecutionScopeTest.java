import javaclasses.compiler.CompilationException;
import javaclasses.compiler.impl.statemachine.executionscope.ExecutionScope;
import org.junit.Test;

public class ExecutionScopeTest {
    final private ExecutionScope executionScope = new ExecutionScope();

    @Test
    public void testEmptyString() throws CompilationException {
        executionScope.compile("a=1;b=3;");
    }

}
