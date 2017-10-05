package steps;

import cucumber.api.Delimiter;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import workers.SqlWorker;

import java.util.List;

public class SqlSteps {

    private static List<String> arg;

    @When("^i put new book in base \\\"([^\\\"]*)\\\"")
    public void newBook(@Delimiter(",") List<String> arg) {
        this.arg = arg;
        SqlWorker.write(arg);
    }

    @Then("^this book appears in base$")
    public void check() {
        Assert.assertEquals(SqlWorker.check(arg), true);
    }

}
