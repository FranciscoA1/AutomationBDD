package pages;


import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

import static java.lang.Thread.sleep;

public class FormularioPage extends BasePage {

    private final By FormNombre = By.xpath("//*[@id=\"user-name\"]");

    @Override
    public void waitPageLoad() {

    }

    public void verifyPage() {
        Logs.info("Verificando la UI de la pagina");
    }



    public void goToFormularioPage() {
        Logs.info("El usuario navega hasta la pagina del formulario");
        getDriver().get("https://www.saucedemo.com/");
    }


    public void llenarFormulario(String producto) {
        Logs.info("Llenar formulario");
        find(FormNombre).sendKeys(producto);
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
