package nl.alten.endegraaf.bdd.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.alten.endegraaf.openkm.database.DbClient;
import nl.alten.endegraaf.openkm.rest.RestClient;
import org.junit.Assert;

public class WorkflowSteps {

    private RestClient restClient;
    private DbClient dbClient;
    private int actualAmountOfSubfolders;

    @Given("^I have a session")
    public void i_have_a_calculator() throws Throwable {
        restClient = new RestClient();
    }
    @Given("^There are no folders under the root folder")
    public void there_are_no_subfolders_under_the_root_folder() {
        dbClient = new DbClient();
        dbClient.removeAllSubFoldersFromRoot();
    }


    @When("^I start a workflow with (.*) and (.*)$")
    public void i_add_fkjsd_and_dlfkd(String condition1, String condition2) throws Throwable {
        actualAmountOfSubfolders = restClient.CountSubFoldersInRoot();
    }

    @Then("^the result should be (.*)$")
    public void the_result_should_be_ldssd(String result1) throws Throwable {
        Assert.assertEquals(3, actualAmountOfSubfolders);
    }

}
