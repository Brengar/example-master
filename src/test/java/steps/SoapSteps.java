package steps;

import cucumber.api.Delimiter;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import workers.SoapWorker;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SoapSteps {

    private List<String> result;

    @When("^i send string \\\"([^\\\"]*)\\\"$")
    public void newBook(String arg) {
        result = SoapWorker.checkString(arg);
    }

    @Then("^the result is \\\"([^\\\"]*)\\\"$")
    public void the_result_should_be(@Delimiter(",") List<String> arg) {
        assertEquals(arg, result);
    }

}