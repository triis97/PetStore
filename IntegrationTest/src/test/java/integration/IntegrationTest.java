package integration;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @Test
    void testParallel() {
        final Results results = Runner.path("classpath:integration/tests")
                .outputCucumberJson(true)
                .parallel(10);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
