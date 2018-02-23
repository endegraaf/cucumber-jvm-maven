package nl.alten.endegraaf.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "json:target/cucumber.json" },
        glue = "nl.alten.endegraaf.bdd.steps",
        features = "classpath:cucumber/workflow.feature"
)

public class RunWorkflowTest {
}
