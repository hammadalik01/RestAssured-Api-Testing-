package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    plugin = {
        "pretty",
        "json:target/jsonReports/cucumber.json"
    },
    glue = {"stepDefinitionFile"}
)
public class TestRunner {}