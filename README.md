# mock-slf4j-impl

This library is useful for testing your logging. It is a SLF4J implementation with mock Loggers backed by Mockito.

## Dependency Information

### Maven

(Note: the distribution to Maven Central is pending. In the meantime, you may check this project out and build it locally
using `mvn clean install` to install the build artifact in your local Maven repository.)

Add this Maven dependency to your POM file:

    <dependency>
        <groupId>com.ocarlsen.util</groupId>
        <artifactId>mock-slf4j-impl</artifactId>
        <version>1.0</version>  
        <scope>test</scope>        
    </dependency>

### Gradle

TODO

## Example Code

Consider the class you wish to test:

    class MyLoggingClass {

        private final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

        public void loggingMethod() {
            logger.debug("this is a debug message");
            logger.info("this is an info message");
            logger.warn("this is a warn message");
            logger.error("this is an error message");
        }
    }

To confirm the log events, get the mock from the delegate and test like this:

    @Test
    public void testLogging() {

        // Given
        final MyLoggingClass loggingInstance = new MyLoggingClass();

        // When
        loggingInstance.loggingMethod();

        // Then
        final Logger logger = LoggerFactory.getLogger("MyLoggingClass");
        final Logger mockLogger = ((DelegatingLogger) logger).getDelegate();

        final InOrder inOrder = inOrder(mockLogger);
        inOrder.verify(mockLogger).debug("this is a debug message");
        inOrder.verify(mockLogger).info("this is an info message");
        inOrder.verify(mockLogger).warn("this is a warn message");
        inOrder.verify(mockLogger).error("this is an error message");
        inOrder.verifyNoMoreInteractions();
    }

This example is demonstrated in [ExampleTest](src/test/java/com/ocarlsen/util/ExampleTest.java).

