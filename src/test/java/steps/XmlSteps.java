package steps;

import cucumber.api.Delimiter;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import workers.XmlWorker;

import java.util.List;

public class XmlSteps {

    private static List<String> arg;

    @When("^i put new book in catalog \\\"([^\\\"]*)\\\"$")
    public void newBook(@Delimiter(",") List<String> arg) {
        XmlSteps.arg =arg;
        XmlWorker.write(arg);
    }

    @Then("^this book apperas in catalog$")
    public void checkBook() {
        Assert.assertEquals(XmlWorker.check(arg), true);
    }

}
