package nurzat.junit.extension;


import org.junit.jupiter.api.extension.*;

import java.util.Optional;

public class LoggingExtension implements
        BeforeAllCallback,
        BeforeEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        AfterEachCallback,
        AfterAllCallback,
         TestWatcher{
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("[extension] after all call back " + context.getDisplayName());


    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("[extension] after each call back " + context.getDisplayName());


    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("[extension] after test execution call back " + context.getDisplayName());


    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("[extension] before all call back " + context.getDisplayName());

    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("[extension] before each call back " + context.getDisplayName());


    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("[extension] before test execution call back " + context.getDisplayName());


    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
        System.out.println("[watch] disabled " + context.getDisplayName());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        System.out.println("[watch] passed " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
        System.out.println("[watch] aborted " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        System.out.println("[watch] failed " + context.getDisplayName());
    }
}
