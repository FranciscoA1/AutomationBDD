package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FormularioPage;
import utilities.CommonFLows;
import utilities.Logs;

public class FormularioIngresoStepDefinition {

    private final CommonFLows commonFLows = new CommonFLows();
    private final FormularioPage formularioPage = new FormularioPage();

    @Given("El usuario navega hasta la pagina del formulario")
    public void goToFormularioPage() {
        Logs.info("El usuario navega hasta la pagina del formulario");
        commonFLows.goToUrl();
    }


    @When("El usuario llena el formulario correctamente")
    public void fillFormBasic() {
        formularioPage.llenarFormulario("standard_user");
    }

    @Then("El formulario se envia correctamente y se vi")
    public void verifyForm() {
        formularioPage.verifyPage();
    }
}
